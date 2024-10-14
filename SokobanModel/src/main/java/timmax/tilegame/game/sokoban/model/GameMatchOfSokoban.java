package timmax.tilegame.game.sokoban.model;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.gameobject.XYOffset;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchExtendedDto;

import timmax.tilegame.game.sokoban.model.gameevent.GameEventOneTileSokobanChangeable;
import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses;
import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfSteps;
import timmax.tilegame.game.sokoban.model.gameobject.*;
import timmax.tilegame.game.sokoban.model.route.Route;
import timmax.tilegame.game.sokoban.model.route.Step;

import static timmax.tilegame.basemodel.GameMatchStatus.FORCE_RESTART_OR_CHANGE_LEVEL;
import static timmax.tilegame.basemodel.gameobject.XYOffset.*;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile.*;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoPersistentInTile.IS_EMPTY;

public class GameMatchOfSokoban<ClientId> extends GameMatch<ClientId> {
    private static LevelLoader levelLoader;

    private final CurrentLevel currentLevel = new CurrentLevel();

    private int countOfBoxesInHomes;
    private Route route;
    private Route routeRedo = new Route();

    static {
        try {
            levelLoader = new LevelLoader(
                    Paths.get(
                            Objects.requireNonNull(
                                            GameMatchOfSokoban.class.getResource("levels.txt"))
                                    .toURI()
                    )
            );
        } catch (URISyntaxException e) {
            logger.error("There is a problem with file with game levels.", e);
            // ToDo: При 'System.exit(1);' сервер закроется. Но ошибка произошла при загрузке только модели одной игры.
            //       Поэтому нужно чтобы только эта модель не загрузилась и клиенту должен быть отправлен перечень игр
            //       без этой игры.
            System.exit(1);
        }
    }

    // ToDo: См. комментарии о согласовании параметров в
    //       - GameType :: GameType(...)
    //       и в
    //       - GameMatchLoader :: getCollectionOfGameType(...)
    public GameMatchOfSokoban(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton)
            throws ClassNotFoundException, NoSuchMethodException {
        super(new GameTypeOfSokoban(), remoteClientStateAutomaton);
    }

    private void moveUndo() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        if (route.size() <= 0) {
            return;
        }

        Step step = route.pop();
        SGOPlayer player = getGameObjectsPlacement().getPlayer();

        WhoMovableInTile oldWhoMovableInTile;
        if (step.isBoxMoved()) {
            for (SGOBox box : getGameObjectsPlacement().getBoxes()) {
                if (box.isCollision(player, step.oppositeStepDirection())) {
                    XYCoordinate xyCoordinateOld = box.getXyCoordinate();
                    box.move(step.oppositeStepDirection());

                    WhoPersistentInTile oldWhoPersistentInTile = getGameObjectsPlacement().getWhoPersistentInTile(xyCoordinateOld);
                    sendGameEventToAllViews(new GameEventOneTileSokobanChangeable(xyCoordinateOld, oldWhoPersistentInTile, IS_NOBODY));

                    break;
                }
            }
            oldWhoMovableInTile = IS_BOX;
        } else {
            oldWhoMovableInTile = IS_NOBODY;
        }

        XYOffset xyOffset = step.oppositeStepDirection();

        addGameEventAboutPlayer(xyOffset, player, oldWhoMovableInTile);
        routeRedo.push(step);

        sendGameEventToAllViews(new GameEventSokobanVariableParamsCountOfSteps(route.size()));
        if (step.isBoxMoved()) {
            calcCountOfBoxesInHomes();
            sendGameEventToAllViews(new GameEventSokobanVariableParamsCountOfBoxesInHouses(countOfBoxesInHomes));
        }
    }

    private void move(XYOffset xyOffset) {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        movePlayerIfPossible(xyOffset, false);
        routeRedo = new Route();

        setStatusIsGame();
    }

    private void moveRedo() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        if (routeRedo.size() <= 0) {
            return;
        }
        Step step = routeRedo.pop();
        movePlayerIfPossible(step.getXyOffset(), true);
    }

    private boolean isCollisionWithWall(CollisionObject gameObject, XYOffset xyOffset) {
        for (SGOWall wall : getGameObjectsPlacement().getWalls()) {
            if (gameObject.isCollision(wall, xyOffset)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCollisionWithBox(CollisionObject gameObject, XYOffset xyOffset) {
        for (SGOBox box : getGameObjectsPlacement().getBoxes()) {
            if (gameObject.isCollision(box, xyOffset)) {
                return true;
            }
        }
        return false;
    }

    private void movePlayerIfPossible(XYOffset xyOffset, boolean isRedo) {
        SGOPlayer player = getGameObjectsPlacement().getPlayer();
        if (!isRedo) {
            if (player.isOutOfBoard(xyOffset)) {
                return;
            }
            if (isCollisionWithWall(player, xyOffset)) {
                return;
            }
        }
        boolean isBoxMoved = false;

        for (SGOBox box : getGameObjectsPlacement().getBoxes()) {
            if (player.isCollision(box, xyOffset)) {
                if (!isRedo) {
                    if (box.isOutOfBoard(xyOffset)) {
                        return;
                    }
                    if (isCollisionWithWall(box, xyOffset)) {
                        return;
                    }
                    if (isCollisionWithBox(box, xyOffset)) {
                        return;
                    }
                }
                box.move(xyOffset);

                WhoPersistentInTile newWhoPersistentInTile = getGameObjectsPlacement().getWhoPersistentInTile(box.getXyCoordinate());
                sendGameEventToAllViews(new GameEventOneTileSokobanChangeable(box.getXyCoordinate(), newWhoPersistentInTile, IS_BOX));

                isBoxMoved = true;
                break;
            }
        }

        addGameEventAboutPlayer(xyOffset, player, IS_NOBODY);
        route.push(new Step(xyOffset, isBoxMoved));

        sendGameEventToAllViews(new GameEventSokobanVariableParamsCountOfSteps(route.size()));
        if (isBoxMoved) {
            calcCountOfBoxesInHomes();
            sendGameEventToAllViews(new GameEventSokobanVariableParamsCountOfBoxesInHouses(countOfBoxesInHomes));
        }

        checkCompletion();
    }

    private void addGameEventAboutPlayer(XYOffset xyOffset, SGOPlayer player, WhoMovableInTile whoMovableInTile) {
        WhoPersistentInTile whoPersistentInTileBefore = getGameObjectsPlacement().getWhoPersistentInTile(player.getXyCoordinate());
        sendGameEventToAllViews(new GameEventOneTileSokobanChangeable(player.getXyCoordinate(), whoPersistentInTileBefore, whoMovableInTile));

        player.move(xyOffset);

        WhoPersistentInTile whoPersistentInTileAfter = getGameObjectsPlacement().getWhoPersistentInTile(player.getXyCoordinate());
        sendGameEventToAllViews(new GameEventOneTileSokobanChangeable(player.getXyCoordinate(), whoPersistentInTileAfter, IS_PLAYER));
    }

    private void calcCountOfBoxesInHomes() {
        int countOfBoxesInHomes = 0;
        for (SGOHome home : getGameObjectsPlacement().getHomes()) {
            for (SGOBox box : getGameObjectsPlacement().getBoxes()) {
                if (box.getXyCoordinate().equals(home.getXyCoordinate())) {
                    countOfBoxesInHomes++;
                    break;
                }
            }
        }
        this.countOfBoxesInHomes = countOfBoxesInHomes;
    }

    private void checkCompletion() {
        // Этот метод должен проверить, пройден ли уровень (т.е. на всех ли домах стоят ящики?).
        // Если условие выполнено, то проинформировать слушателей событий, что текущий уровень завершен.
        if (countOfBoxesInHomes == getGameObjectsPlacement().getCountOfPairHomesAndBoxes()) {
            win();
        }
    }

    public void nextLevel() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setStatus(FORCE_RESTART_OR_CHANGE_LEVEL);
        currentLevel.incValue();
        sendGameEventToAllViews(new GameEventGameOver(FORCE_RESTART_OR_CHANGE_LEVEL));
    }

    public void prevLevel() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setStatus(FORCE_RESTART_OR_CHANGE_LEVEL);
        currentLevel.decValue();
        sendGameEventToAllViews(new GameEventGameOver(FORCE_RESTART_OR_CHANGE_LEVEL));
    }

    // interface IGameMatchX
    @Override
    public void setParamsOfModelValueMap(Map<String, Integer> paramsOfModelValueMap) {
        throwExceptionIfIsPlaying();

        setGameObjectsPlacement(levelLoader.getLevel(currentLevel.getValue(), this));
        super.setParamsOfModelValueMap(
                Map.of(PARAM_NAME_WIDTH,
                        getGameObjectsPlacement().getWidthHeightSizes().getWidth(),
                        PARAM_NAME_HEIGHT,
                        getGameObjectsPlacement().getWidthHeightSizes().getHeight()
                )
        );
    }

    @Override
    public GameMatchExtendedDto start(GameMatchExtendedDto gameMatchExtendedDto) {
        // ToDo: Что-то из описанного ниже ToDo сделать здесь, что-то в родительском классе.
        // ToDo: Отправить клиенту:
        //       1. Размеры главной выборки матча и умолчательные характеристики для построение пустого поля
        //          (но возможно, это в более раннем событии следует передать) для построения пустой выборки главного поля.
        //       2. Объекты матча статические (например для Сокобана: стены или дома).
        //       3. Объекты матча динамические. Например:
        //          - для Сокобана: игрок, ящики.
        //          - для Сапёра: флаги и количество мин на открытых плитках.

        throwExceptionIfIsPlaying();

        // В этой реализации Сокобан не обращаем внимание на gameMatchExtendedDto - просто загружаем следующий уровень.
        setGameObjectsPlacement(levelLoader.getLevel(currentLevel.getValue(), this));
        super.setParamsOfModelValueMap(
                Map.of(PARAM_NAME_WIDTH,
                        getGameObjectsPlacement().getWidthHeightSizes().getWidth(),
                        PARAM_NAME_HEIGHT,
                        getGameObjectsPlacement().getWidthHeightSizes().getHeight()
                )
        );

        Set<GameEventOneTile> gameEventOneTileSet = new HashSet<>();

        for (int y = 0; y < getGameObjectsPlacement().getWidthHeightSizes().getHeight(); y++) {
            for (int x = 0; x < getGameObjectsPlacement().getWidthHeightSizes().getWidth(); x++) {
                XYCoordinate xyCoordinate = new XYCoordinate(x, y);
                WhoPersistentInTile whoPersistentInTile = getGameObjectsPlacement().getWhoPersistentInTile(xyCoordinate);
                WhoMovableInTile whoMovableInTile = getGameObjectsPlacement().getWhoMovableInTile(xyCoordinate);
                // Это чтобы меньше было событий - про пустые плитки не делаем события.
                if (whoPersistentInTile == IS_EMPTY && whoMovableInTile == IS_NOBODY) {
                    continue;
                }
                GameEventOneTile gameEventOneTile = new GameEventOneTileSokobanChangeable(xyCoordinate, whoPersistentInTile, whoMovableInTile);
                gameEventOneTileSet.add(gameEventOneTile);
            }
        }
        route = new Route();
        routeRedo = new Route();
        calcCountOfBoxesInHomes();

        return newGameMatchExtendedDto(gameEventOneTileSet);
    }

    // interface IGameMatch
    @Override
    public void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick) {
        if (gameCommandMouseClick.getMouseButton() == MouseButton.PRIMARY) {
            XYCoordinate xyCoordinateOfMouseClick = gameCommandMouseClick.getXYCoordinate();
            XYCoordinate xyCoordinateOfPlayer = getGameObjectsPlacement().getPlayer().getXyCoordinate();
            if (xyCoordinateOfMouseClick.hasEqualY(xyCoordinateOfPlayer)) {
                if (xyCoordinateOfMouseClick.hasXLess(xyCoordinateOfPlayer)) {
                    move(TO_LEFT);
                } else
                    if (xyCoordinateOfMouseClick.hasXMore(xyCoordinateOfPlayer)) {
                        move(TO_RIGHT);
                    }
            } else
                if ((xyCoordinateOfMouseClick.hasEqualX(xyCoordinateOfPlayer))) {
                    if (xyCoordinateOfMouseClick.hasYLess(xyCoordinateOfPlayer)) {
                        move(TO_UP);
                    } else
                        if (xyCoordinateOfMouseClick.hasYMore(xyCoordinateOfPlayer)) {
                            move(TO_DOWN);
                        }
                }
        } else if (gameCommandMouseClick.getMouseButton() == MouseButton.SECONDARY) {
            moveUndo();
        } else if (gameCommandMouseClick.getMouseButton() == MouseButton.MIDDLE) {
            moveRedo();
        }
    }

    @Override
    public void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed) {
        if (gameCommandKeyPressed.getKeyCode() == KeyCode.LEFT) {
            move(TO_LEFT);
        } else if (gameCommandKeyPressed.getKeyCode() == KeyCode.RIGHT) {
            move(TO_RIGHT);
        } else if (gameCommandKeyPressed.getKeyCode() == KeyCode.UP) {
            move(TO_UP);
        } else if (gameCommandKeyPressed.getKeyCode() == KeyCode.DOWN) {
            move(TO_DOWN);
        } else if (gameCommandKeyPressed.getKeyCode() == KeyCode.Q) {
            moveUndo();
        } else if (gameCommandKeyPressed.getKeyCode() == KeyCode.P) {
            moveRedo();
        } else if (gameCommandKeyPressed.getKeyCode() == KeyCode.BACK_SPACE) {
            prevLevel();
        } else if (gameCommandKeyPressed.getKeyCode() == KeyCode.SPACE) {
            nextLevel();
        } else if (gameCommandKeyPressed.getKeyCode() == KeyCode.ESCAPE) {
            restart();
        }
    }

    @Override
    public void win() {
        currentLevel.incValue();
        super.win();
    }

    @Override
    public SokobanPlacement getGameObjectsPlacement() {
        return (SokobanPlacement) super.getGameObjectsPlacement();
    }
}
