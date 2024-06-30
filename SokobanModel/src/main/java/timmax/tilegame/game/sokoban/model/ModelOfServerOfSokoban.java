package timmax.tilegame.game.sokoban.model;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.basemodel.protocol.server.ParamOfModelDescription;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.basemodel.tile.Direction;

import timmax.tilegame.game.sokoban.model.gameevent.GameEventOneTileSokobanChangeable;
import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses;
import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfSteps;
import timmax.tilegame.game.sokoban.model.gameobject.*;
import timmax.tilegame.game.sokoban.model.route.Route;
import timmax.tilegame.game.sokoban.model.route.Step;

// import static timmax.tilegame.basemodel.GameStatus.FORCE_RESTART_OR_CHANGE_LEVEL;
import static javafx.scene.paint.Color.*;
import static timmax.tilegame.basemodel.GameStatus.FORCE_RESTART_OR_CHANGE_LEVEL;
import static timmax.tilegame.game.sokoban.model.gameobject.WhoMovableInTile.*;

public class ModelOfServerOfSokoban<ClientId> extends ModelOfServer<ClientId> {
    // Константы, описанные ниже, относятся к визуализации.
    // ToDo: Вынести логику визуализации из класса.
    public static final Color WALL_CELL_COLOR = RED;
    public static final Color HOME_CELL_COLOR = WHITE;
    public static final Color EMPTY_CELL_COLOR = BLACK;

    public static final String PLAYER = "😀"; // "\uF9CD"; // "&";
    public static final Color PLAYER_TEXT_COLOR = GREEN;

    public static final String BOX = "█"; // "❐"; // "▉"; // "[]";
    public static final Color BOX_TEXT_COLOR = BLUE;

    private static LevelLoader levelLoader;

    private final CurrentLevel currentLevel = new CurrentLevel();

    private AllSokobanObjects allSokobanObjects;
    private int countOfBoxesInHomes;
    private Route route;
    private Route routeRedo = new Route();

    static {
        try {
            levelLoader = new LevelLoader(Paths.get(Objects.requireNonNull(ModelOfServerOfSokoban.class.getResource("levels.txt")).toURI()));
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
    //       - ModelOfServerLoader :: getCollectionOfGameType(...)
    public ModelOfServerOfSokoban(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        super(remoteClientStateAutomaton, clientId);
    }

    private void moveUndo() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        if (route.size() <= 0) {
            return;
        }

        Step step = route.pop();
        Player player = allSokobanObjects.getPlayer();

        int oldBoxX;
        int oldBoxY;
        WhoMovableInTile oldWhoMovableInTile;
        if (step.isBoxMoved()) {
            for (Box box : allSokobanObjects.getBoxes()) {
                if (box.isCollision(player, step.oppositeStepDirection())) {
                    oldBoxX = box.getX();
                    oldBoxY = box.getY();
                    box.move(step.oppositeStepDirection());

                    WhoPersistentInTile oldWhoPersistentInTile = allSokobanObjects.getWhoPersistentInTile(oldBoxX, oldBoxY);
                    sendGameEvent(new GameEventOneTileSokobanChangeable(oldBoxX, oldBoxY, oldWhoPersistentInTile, IS_NOBODY));

                    break;
                }
            }
            oldWhoMovableInTile = IS_BOX;
        } else {
            oldWhoMovableInTile = IS_NOBODY;
        }

        Direction direction = step.oppositeStepDirection();

        addGameEventAboutPlayer(direction, player, oldWhoMovableInTile);
        routeRedo.push(step);

        sendGameEvent(new GameEventSokobanVariableParamsCountOfSteps(route.size()));
        if (step.isBoxMoved()) {
            calcCountOfBoxesInHomes();
            sendGameEvent(new GameEventSokobanVariableParamsCountOfBoxesInHouses(countOfBoxesInHomes));
        }
    }

    private void move(Direction direction) {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        movePlayerIfPossible(direction, false);
        routeRedo = new Route();
    }

    private void moveRedo() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        if (routeRedo.size() <= 0) {
            return;
        }
        Step step = routeRedo.pop();
        movePlayerIfPossible(step.getDirection(), true);
    }

    private boolean isCollisionWithWall(CollisionObject gameObject, Direction direction) {
        for (Wall wall : allSokobanObjects.getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCollisionWithBox(CollisionObject gameObject, Direction direction) {
        for (Box box : allSokobanObjects.getBoxes()) {
            if (gameObject.isCollision(box, direction)) {
                return true;
            }
        }
        return false;
    }

    private void movePlayerIfPossible(Direction direction, boolean isRedo) {
        Player player = allSokobanObjects.getPlayer();
        if (!isRedo) {
            if (player.isOutOfBoard(direction, allSokobanObjects.getWidth(), allSokobanObjects.getHeight())) {
                return;
            }
            if (isCollisionWithWall(player, direction)) {
                return;
            }
        }
        boolean isBoxMoved = false;
        int newBoxX;
        int newBoxY;

        for (Box box : allSokobanObjects.getBoxes()) {
            if (player.isCollision(box, direction)) {
                if (!isRedo) {
                    if (box.isOutOfBoard(direction, allSokobanObjects.getWidth(), allSokobanObjects.getHeight())) {
                        return;
                    }
                    if (isCollisionWithWall(box, direction)) {
                        return;
                    }
                    if (isCollisionWithBox(box, direction)) {
                        return;
                    }
                }
                box.move(direction);

                newBoxX = box.getX();
                newBoxY = box.getY();

                WhoPersistentInTile newWhoPersistentInTile = allSokobanObjects.getWhoPersistentInTile(newBoxX, newBoxY);
                sendGameEvent(new GameEventOneTileSokobanChangeable(newBoxX, newBoxY, newWhoPersistentInTile, IS_BOX));

                isBoxMoved = true;
                break;
            }
        }

        addGameEventAboutPlayer(direction, player, IS_NOBODY);
        route.push(new Step(direction, isBoxMoved));

        sendGameEvent(new GameEventSokobanVariableParamsCountOfSteps(route.size()));
        if (isBoxMoved) {
            calcCountOfBoxesInHomes();
            sendGameEvent(new GameEventSokobanVariableParamsCountOfBoxesInHouses(countOfBoxesInHomes));
        }

        checkCompletion();
    }

    private void addGameEventAboutPlayer(Direction direction, Player player, WhoMovableInTile whoMovableInTile) {
        int x;
        int y;
        WhoPersistentInTile whoPersistentInTile;

        x = player.getX();
        y = player.getY();
        whoPersistentInTile = allSokobanObjects.getWhoPersistentInTile(x, y);
        sendGameEvent(new GameEventOneTileSokobanChangeable(x, y, whoPersistentInTile, whoMovableInTile));

        player.move(direction);

        x = player.getX();
        y = player.getY();
        whoPersistentInTile = allSokobanObjects.getWhoPersistentInTile(x, y);
        sendGameEvent(new GameEventOneTileSokobanChangeable(x, y, whoPersistentInTile, IS_PLAYER));
    }

    private void calcCountOfBoxesInHomes() {
        int countOfBoxesInHomes = 0;
        for (Home home : allSokobanObjects.getHomes()) {
            for (Box box : allSokobanObjects.getBoxes()) {
                if (box.getX() == home.getX() && box.getY() == home.getY()) {
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
        if (countOfBoxesInHomes == allSokobanObjects.getCountOfHomesBoxes()) {
            win();
        }
    }

    public void nextLevel() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setGameStatus(FORCE_RESTART_OR_CHANGE_LEVEL);
        currentLevel.incValue();
        sendGameEvent(new GameEventGameOver(FORCE_RESTART_OR_CHANGE_LEVEL));
    }

    public void prevLevel() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setGameStatus(FORCE_RESTART_OR_CHANGE_LEVEL);
        currentLevel.decValue();
        sendGameEvent(new GameEventGameOver(FORCE_RESTART_OR_CHANGE_LEVEL));
    }

    // Overiden methods from interface IModelOfServer extends IGameType:
    @Override
    public String getGameName() {
        return "Sokoban";
    }

    @Override
    public int getCountOfGamers() {
        return 1;
    }

    @Override
    public Map<String, ParamOfModelDescription> getMapOfParamsOfModelDescription() {
        return null;
    }

    // Overiden methods from interface IModelOfServer:
    @Override
    public void createNewGame() {
        if (allSokobanObjects != null && getGameStatus() == GameStatus.GAME) {
            return;
        }

        allSokobanObjects = levelLoader.getLevel(currentLevel.getValue());
        super.createNewGame(allSokobanObjects.getWidth(), allSokobanObjects.getHeight());
        for (int y = 0; y < allSokobanObjects.getHeight(); y++) {
            for (int x = 0; x < allSokobanObjects.getWidth(); x++) {
                WhoPersistentInTile whoPersistentInTile = allSokobanObjects.getWhoPersistentInTile(x, y);
                WhoMovableInTile whoMovableInTile = allSokobanObjects.getWhoMovableInTile(x, y);
                sendGameEvent(new GameEventOneTileSokobanChangeable(x, y, whoPersistentInTile, whoMovableInTile));
            }
        }
        route = new Route();
        routeRedo = new Route();
        // Инициализация информационных выборок.
        // sendGameEvent(new GameEventSokobanPersistentParams(allSokobanObjects.getCountOfHomesBoxes()));
        // sendGameEvent(new GameEventSokobanVariableParamsCountOfSteps(0));
        calcCountOfBoxesInHomes();
        // sendGameEvent(new GameEventSokobanVariableParamsCountOfBoxesInHouses(countOfBoxesInHomes));
    }

    @Override
    public void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick) {
        if (gameCommandMouseClick.getMouseButton() == MouseButton.PRIMARY) {
            if (gameCommandMouseClick.getY() == allSokobanObjects.getPlayer().getY()) {
                if (gameCommandMouseClick.getX() < allSokobanObjects.getPlayer().getX()) {
                    move(Direction.LEFT);
                } else if (gameCommandMouseClick.getX() > allSokobanObjects.getPlayer().getX()) {
                    move(Direction.RIGHT);
                }
            } else if ((gameCommandMouseClick.getX() == allSokobanObjects.getPlayer().getX())) {
                if (gameCommandMouseClick.getY() < allSokobanObjects.getPlayer().getY()) {
                    move(Direction.UP);
                } else if (gameCommandMouseClick.getY() > allSokobanObjects.getPlayer().getY()) {
                    move(Direction.DOWN);
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
            move(Direction.LEFT);
        } else if (gameCommandKeyPressed.getKeyCode() == KeyCode.RIGHT) {
            move(Direction.RIGHT);
        } else if (gameCommandKeyPressed.getKeyCode() == KeyCode.UP) {
            move(Direction.UP);
        } else if (gameCommandKeyPressed.getKeyCode() == KeyCode.DOWN) {
            move(Direction.DOWN);
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
}
