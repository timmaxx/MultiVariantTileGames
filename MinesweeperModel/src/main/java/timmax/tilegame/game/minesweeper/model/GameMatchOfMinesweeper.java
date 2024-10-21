package timmax.tilegame.game.minesweeper.model;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.gameobject.GameObjectStateAutomaton;
import timmax.tilegame.basemodel.gameobject.WidthHeightSizes;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

import timmax.tilegame.basemodel.protocol.server_client.GameMatchExtendedDto;
import timmax.tilegame.game.minesweeper.model.gameobject.LevelGenerator;
import timmax.tilegame.game.minesweeper.model.gameobject.MGOStateAutomaton;
import timmax.tilegame.game.minesweeper.model.gameobject.MinesweeperPlacement;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameMatchOfMinesweeper<ClientId> extends GameMatch<ClientId> {
    //  1.  String constants
    public static final String PARAM_NAME_PERCENTS_OF_MINES = "Percents of mines";

    //  2.  Level generator/loader
    private static final LevelGenerator levelGenerator;

    //  3.  Переменные экземпляра
    //      Их нет.

    //  4.  Инициализатор Level generator/loader
    static {
        levelGenerator = new LevelGenerator();
    }

    // ToDo: См. комментарии о согласовании параметров в
    //       - GameType :: GameType(...)
    //       и в
    //       - GameMatchLoader :: getCollectionOfGameType(...)
    public GameMatchOfMinesweeper(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton)
            throws ClassNotFoundException, NoSuchMethodException {
        super(new GameTypeOfMinesweeper(), remoteClientStateAutomaton);
    }

    // interface IGameMatchX:
    @Override
    public void setParamsOfModelValueMap(Map<String, Integer> paramsOfModelValueMap) {
        throwExceptionIfIsPlaying();

        super.setParamsOfModelValueMap(paramsOfModelValueMap);
        gameObjectsPlacement = levelGenerator.getLevel(
                this,
                //  ToDo:   Переделать getWidth(), getHeight() в родительском классе.
                //          Пусть там будет переменная WidthHeightSizes.
                new WidthHeightSizes(getWidth(), getHeight()),
                paramsOfModelValueMap.get(PARAM_NAME_PERCENTS_OF_MINES)
        );
    }

    @Override
    public GameMatchExtendedDto start(GameMatchExtendedDto gameMatchExtendedDto) {
        //  ToDo:   Что-то из описанного ниже ToDo сделать здесь, что-то в родительском классе.
        //  ToDo:   Отправить клиенту:
        //          1. Размеры главной выборки матча и умолчательные характеристики для построения пустого поля
        //             (но возможно, это в более раннем событии следует передать) для построения пустой выборки главного поля.
        //          2. Объекты матча статические (например для Сокобана: стены или дома).
        //          3. Объекты матча динамические. Например:
        //              - для Сокобана: игрок, ящики.
        //              - для Сапёра: флаги и количество мин на открытых плитках.

        throwExceptionIfIsPlaying();

        super.start(gameMatchExtendedDto);
        gameObjectsPlacement = levelGenerator.getLevel(
                this,
                //  ToDo:   Переделать getWidth(), getHeight() в родительском классе.
                //          Пусть там будет переменная WidthHeightSizes.
                new WidthHeightSizes(getWidth(), getHeight()),
                getFromParamsOfModelValueMap(PARAM_NAME_PERCENTS_OF_MINES)
        );

        return newGameMatchExtendedDto(new HashSet<>());
    }

    // interface IGameMatch
    @Override
    public void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick) {
        XYCoordinate xyCoordinate = gameCommandMouseClick.getXYCoordinate();
        //  Найдём объект по координатам
        Set<GameObjectStateAutomaton> gameObjectStateAutomatonSet =
                getGameObjectsPlacement()
                        .getGameObjectStateAutomatonSetFilteredXYCoordinate(xyCoordinate);
        GameObjectStateAutomaton gameObjectStateAutomaton = gameObjectStateAutomatonSet
                .stream()
                .findFirst()
                .orElse(null);
        MGOStateAutomaton MGOStateAutomaton =
                (MGOStateAutomaton) gameObjectStateAutomaton;
        if (gameCommandMouseClick.getMouseButton() == MouseButton.PRIMARY) {
            //  Откроем объект
            MGOStateAutomaton.open();
        } else if (gameCommandMouseClick.getMouseButton() == MouseButton.SECONDARY) {
            //  Инвертируем флаг объекту
            MGOStateAutomaton.inverseFlag();
        }
    }

    @Override
    public void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed) {
        if (gameCommandKeyPressed.getKeyCode() == KeyCode.ESCAPE) {
            restart();
        }
    }

    @Override
    public MinesweeperPlacement getGameObjectsPlacement() {
        return (MinesweeperPlacement) super.getGameObjectsPlacement();
    }
}
