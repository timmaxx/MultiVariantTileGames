package timmax.tilegame.sokoban.model;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.gameevent.GameEventGameOver;
import timmax.tilegame.basemodel.gameevent.GameEventOneTile;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.sokoban.model.placement.placementstate.SokobanPlacementStateAutomaton;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;
import timmax.tilegame.basemodel.dto.GameMatchExtendedDto;

import timmax.tilegame.sokoban.model.gameevent.GameEventOneTileSokobanChangeable;
import timmax.tilegame.sokoban.model.placement.gameobject.WhoMovableInTile;
import timmax.tilegame.sokoban.model.placement.gameobject.WhoPersistentInTile;

import static javafx.scene.input.KeyCode.*;
import static javafx.scene.input.MouseButton.PRIMARY;
import static javafx.scene.paint.Color.*;
import static timmax.tilegame.basemodel.GameMatchStatus.FORCE_RESTART_OR_CHANGE_LEVEL;
import static timmax.tilegame.sokoban.model.placement.gameobject.WhoMovableInTile.IS_NOBODY;
import static timmax.tilegame.sokoban.model.placement.gameobject.WhoPersistentInTile.IS_EMPTY;

public class GameMatchOfSokoban extends GameMatch {
    //  1.  String constants
    //      Нет.

    //  2.  static pathToLevels
    private static final Path pathToLevels;

    //  3.  Переменные экземпляра
    private final CurrentLevel currentLevel = new CurrentLevel();
    // private Route route;
    // private Route routeRedo = new Route();

    //  4.  Инициализатор pathToLevels
    static {
        try {
            pathToLevels = Paths.get(Objects
                    .requireNonNull(GameMatchOfSokoban.class.getResource("levels.txt"))
                    .toURI()
            );
        } catch (URISyntaxException uriSE) {
            logger.error("There is a problem with file with game levels.", uriSE);
            //  ToDo:   При 'System.exit(1);' сервер закроется.
            //          Но ошибка произошла при загрузке только модели одной игры.
            //          Поэтому нужно чтобы только эта модель не загрузилась
            //          и клиенту должен быть отправлен перечень игр без этой игры.
            System.exit(1);

            throw new RuntimeException(uriSE);
        }
    }

    //  ToDo:   Удалить отсюда константы, описанные ниже, т.к. они относятся к визуализации.
    public static final Color WALL_BACKGROUND_COLOR = RED;
    public static final Color HOME_BACKGROUND_COLOR = WHITE;
    public static final Color EMPTY_BACKGROUND_COLOR = BLACK;

    public static final String NOBODY_TEXT = "";
    public static final Color NOBODY_TEXT_COLOR = BLACK;

    public static final String PLAYER_TEXT = "😀"; // "\uF9CD"; // "&";
    public static final Color PLAYER_TEXT_COLOR = GREEN;

    public static final String BOX_TEXT = "█"; // "❐"; // "▉"; // "[]";
    public static final Color BOX_TEXT_COLOR = BLUE;

    // ToDo: См. комментарии о согласовании параметров в
    //       - GameType :: GameType(...)
    //       и в
    //       - GameMatchLoader :: getCollectionOfGameType(...)
    public GameMatchOfSokoban(RemoteClientStateAutomaton remoteClientStateAutomaton)
            throws ClassNotFoundException, NoSuchMethodException {
        super(new GameTypeOfSokoban(), remoteClientStateAutomaton);
        super.setPlayer(0, remoteClientStateAutomaton.getUser());
    }

    public void nextLevel() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setStatus(FORCE_RESTART_OR_CHANGE_LEVEL);
        currentLevel.incValue();
        //  ToDo:   Вместо
        //          getRemoteClientStateAutomaton().getSenderOfEventOfServer()
        //          сделать getSenderOfEventOfServer(), который будет доставаться сразу из свойств сервера.
        getRemoteClientStateAutomaton().getSenderOfEventOfServer().sendGameEventToAllViews(
                getRemoteClientStateAutomaton().getGameMatchX().getMatchPlayerList(),
                new GameEventGameOver(FORCE_RESTART_OR_CHANGE_LEVEL),
                getGameType().getViewName_ViewClassMap()
        );
    }

    public void prevLevel() {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setStatus(FORCE_RESTART_OR_CHANGE_LEVEL);
        currentLevel.decValue();
        //  ToDo:   Вместо
        //          getRemoteClientStateAutomaton().getSenderOfEventOfServer()
        //          сделать getSenderOfEventOfServer(), который будет доставаться сразу из свойств сервера.
        getRemoteClientStateAutomaton().getSenderOfEventOfServer().sendGameEventToAllViews(
                getRemoteClientStateAutomaton().getGameMatchX().getMatchPlayerList(),
                new GameEventGameOver(FORCE_RESTART_OR_CHANGE_LEVEL),
                //  Warning:(92, 17) Unchecked assignment: 'java.util.Map' to 'java.util.Map<java.lang.String,java.lang.Class<? extends timmax.tilegame.baseview.View>>'. Reason: 'getGameType()' has raw type, so result of getViewName_ViewClassMap is erased
                getGameType().getViewName_ViewClassMap()
        );
    }

    // interface IGameMatchX
    @Override
    public void start(GameMatchExtendedDto gameMatchExtendedDto) {
        // ToDo: Что-то из описанного ниже ToDo сделать здесь, что-то в родительском классе.
        // ToDo: Отправить клиенту:
        //       1. Размеры главной выборки матча и умолчательные характеристики для построения пустого поля
        //          (но возможно, это в более раннем событии следует передать) для построения пустой выборки главного поля.
        //       2. Объекты матча статические (например для Сокобан: стены или дома).
        //       3. Объекты матча динамические. Например:
        //          - для Сокобан: игрок, ящики.

        super.start(gameMatchExtendedDto);

        // 1. setGameObjectsPlacement(levelLoader.getLevel())
        // 2. setParamsOfModelValueMap()
        // 3. подготовка перечня событий для отправки клиенту для прорисовки расстановки.

        // В этой реализации Сокобан не обращаем внимание на gameMatchExtendedDto - просто загружаем следующий уровень.
        setGameObjectsPlacementStateAutomaton(
                new SokobanPlacementStateAutomaton(
                        this, pathToLevels, currentLevel.getValue()
                )
        );
        setParamsOfModelValueMap(
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
                if (whoPersistentInTile == IS_EMPTY && whoMovableInTile == IS_NOBODY) {
                    continue;
                }
                GameEventOneTile gameEventOneTile = new GameEventOneTileSokobanChangeable(xyCoordinate, whoPersistentInTile, whoMovableInTile);
                gameEventOneTileSet.add(gameEventOneTile);
            }
        }
        // route = new Route();
        // routeRedo = new Route();

        setGameMatchExtendedDto(newGameMatchExtendedDto(gameEventOneTileSet));
    }

    // interface IGameMatch
    @Override
    public void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick) {
        if (gameCommandMouseClick.getMouseButton() == PRIMARY) {
            XYCoordinate xyCoordinateOfMouseClick = gameCommandMouseClick.getXYCoordinate();
            getGameObjectsPlacementStateAutomaton().movePlayerToMouseClick(xyCoordinateOfMouseClick);
        }/* else if (gameCommandMouseClick.getMouseButton() == MouseButton.SECONDARY) {
            moveUndo();
        } else if (gameCommandMouseClick.getMouseButton() == MouseButton.MIDDLE) {
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
        } else*/
        if (gameCommandKeyPressed.getKeyCode() == BACK_SPACE) {
            prevLevel();
        } else if (gameCommandKeyPressed.getKeyCode() == SPACE) {
            nextLevel();
        } else if (gameCommandKeyPressed.getKeyCode() == ESCAPE) {
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
