package timmax.tilegame.sokoban.model;

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
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.sokoban.model.placement.placementstate.SokobanPlacementStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchExtendedDto;

import timmax.tilegame.sokoban.model.gameevent.GameEventOneTileSokobanChangeable;
import timmax.tilegame.sokoban.model.placement.placementstate.LevelLoader;
import timmax.tilegame.sokoban.model.placement.gameobject.WhoMovableInTile;
import timmax.tilegame.sokoban.model.placement.gameobject.WhoPersistentInTile;

import static timmax.tilegame.basemodel.GameMatchStatus.FORCE_RESTART_OR_CHANGE_LEVEL;

public class GameMatchOfSokoban<ClientId> extends GameMatch<ClientId> {
    //  1.  String constants
    //      Их нет.

    //  2.  Level generator/loader
    private static final LevelLoader levelLoader;

    //  3.  Переменные экземпляра
    private final CurrentLevel currentLevel = new CurrentLevel();
    // private Route route;
    // private Route routeRedo = new Route();

    //  4.  Инициализатор Level generator/loader
    static {
        try {
            levelLoader = new LevelLoader(
                    Paths.get(
                            Objects
                                    .requireNonNull(GameMatchOfSokoban.class.getResource("levels.txt"))
                                    .toURI()
                    )
            );
        } catch (URISyntaxException uriSE) {
            logger.error("There is a problem with file with game levels.", uriSE);
            // ToDo: При 'System.exit(1);' сервер закроется. Но ошибка произошла при загрузке только модели одной игры.
            //       Поэтому нужно чтобы только эта модель не загрузилась и клиенту должен быть отправлен перечень игр
            //       без этой игры.
            System.exit(1);

            throw new RuntimeException(uriSE);
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

        //  Здесь, по порядку:
        //  1. setGameObjectsPlacement(levelLoader.getLevel()),
        //  2. super.setParamsOfModelValueMap().
        setGameObjectsPlacementStateAutomaton(levelLoader.getLevel(this, currentLevel.getValue()));
        super.setParamsOfModelValueMap(
                Map.of(PARAM_NAME_WIDTH,
                        getGameObjectsPlacementStateAutomaton().getWidthHeightSizes().getWidth(),
                        PARAM_NAME_HEIGHT,
                        getGameObjectsPlacementStateAutomaton().getWidthHeightSizes().getHeight()
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

        // 1. setGameObjectsPlacement(levelLoader.getLevel())
        // 2. super.setParamsOfModelValueMap()
        // 3. подготовка перечня событий для отправки клиенту для прорисовки расстановки.

        // В этой реализации Сокобан не обращаем внимание на gameMatchExtendedDto - просто загружаем следующий уровень.
        setGameObjectsPlacementStateAutomaton(levelLoader.getLevel(this, currentLevel.getValue()));

        super.setParamsOfModelValueMap(
                Map.of(PARAM_NAME_WIDTH,
                        getGameObjectsPlacementStateAutomaton().getWidthHeightSizes().getWidth(),
                        PARAM_NAME_HEIGHT,
                        getGameObjectsPlacementStateAutomaton().getWidthHeightSizes().getHeight()
                )
        );

        Set<GameEventOneTile> gameEventOneTileSet = new HashSet<>();

        for (int y = 0; y < getGameObjectsPlacementStateAutomaton().getWidthHeightSizes().getHeight(); y++) {
            for (int x = 0; x < getGameObjectsPlacementStateAutomaton().getWidthHeightSizes().getWidth(); x++) {
                XYCoordinate xyCoordinate = new XYCoordinate(x, y);
                WhoPersistentInTile whoPersistentInTile = getGameObjectsPlacementStateAutomaton().getWhoPersistentInTile(xyCoordinate);
                WhoMovableInTile whoMovableInTile = getGameObjectsPlacementStateAutomaton().getWhoMovableInTile(xyCoordinate);
                // Это чтобы меньше было событий - про пустые плитки не делаем события.
                if (whoPersistentInTile == WhoPersistentInTile.IS_EMPTY && whoMovableInTile == WhoMovableInTile.IS_NOBODY) {
                    continue;
                }
                GameEventOneTile gameEventOneTile = new GameEventOneTileSokobanChangeable(xyCoordinate, whoPersistentInTile, whoMovableInTile);
                gameEventOneTileSet.add(gameEventOneTile);
            }
        }
        // route = new Route();
        // routeRedo = new Route();

        return newGameMatchExtendedDto(gameEventOneTileSet);
    }

    // interface IGameMatch
    @Override
    public void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick) {
        if (gameCommandMouseClick.getMouseButton() == MouseButton.PRIMARY) {
            XYCoordinate xyCoordinateOfMouseClick = gameCommandMouseClick.getXYCoordinate();
            getGameObjectsPlacementStateAutomaton().movePlayerToMouseClick(xyCoordinateOfMouseClick);
        }/* else if (gameCommandMouseClick.getMouseButton() == MouseButton.SECONDARY) {
            moveUndo();
        }*/ /*else if (gameCommandMouseClick.getMouseButton() == MouseButton.MIDDLE) {
            moveRedo();
        }*/
    }

    @Override
    public void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed) {
        getGameObjectsPlacementStateAutomaton().movePlayerByKeyCode(gameCommandKeyPressed.getKeyCode());
        /* if (gameCommandKeyPressed.getKeyCode() == KeyCode.Q) {
            moveUndo();
        } else if (gameCommandKeyPressed.getKeyCode() == KeyCode.P) {
            moveRedo();
        } else*/ if (gameCommandKeyPressed.getKeyCode() == KeyCode.BACK_SPACE) {
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
    protected SokobanPlacementStateAutomaton getGameObjectsPlacementStateAutomaton() {
        return (SokobanPlacementStateAutomaton) super.getGameObjectsPlacementStateAutomaton();
    }
}
