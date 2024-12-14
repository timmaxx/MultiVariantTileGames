package timmax.tilegame.minesweeper.model;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.placement.primitives.WidthHeightSizes;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

import timmax.tilegame.basemodel.protocol.server_client.GameMatchExtendedDto;
import timmax.tilegame.minesweeper.model.placement.placementstate.MinesweeperPlacementStateAutomaton;
import timmax.tilegame.minesweeper.model.placement.gameobject.MGOStateAutomaton;

import java.util.Map;

public class GameMatchOfMinesweeper<ClientId> extends GameMatch<ClientId> {
    //  1.  String constants
    public static final String PARAM_NAME_PERCENTS_OF_MINES = "Percents of mines";

    //  2.  static pathToLevels
    //      Нет.

    //  3.  Переменные экземпляра
    //      Нет.

    //  4.  Инициализатор Level generator/loader
    //      Нет.

    // ToDo: См. комментарии о согласовании параметров в
    //       - GameType :: GameType(...)
    //       и в
    //       - GameMatchLoader :: getCollectionOfGameType(...)
    public GameMatchOfMinesweeper(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton)
            throws ClassNotFoundException, NoSuchMethodException {
        super(new GameTypeOfMinesweeper(), remoteClientStateAutomaton);
        super.setPlayer(0, remoteClientStateAutomaton.getUser());
    }

    // interface IGameMatchX:
    @Override
    public void setParamsOfModelValueMap(Map<String, Integer> paramsOfModelValueMap) {
        throwExceptionIfIsPlaying();

        //  Здесь, в таком порядке:
        //  1. super.setParamsOfModelValueMap(),
        //  2. setGameObjectsPlacement(levelGenerator.getLevel()).

        super.setParamsOfModelValueMap(paramsOfModelValueMap);
        setGameObjectsPlacementStateAutomaton(new MinesweeperPlacementStateAutomaton(
                this,
                //  ToDo:   Переделать getWidth(), getHeight() в родительском классе.
                //          Пусть там будет переменная WidthHeightSizes.
                new WidthHeightSizes(getWidth(), getHeight()),
                paramsOfModelValueMap.get(PARAM_NAME_PERCENTS_OF_MINES)
        ));
    }

    @Override
    public void start(GameMatchExtendedDto gameMatchExtendedDto) {
        //  ToDo:   Что-то из описанного ниже ToDo сделать здесь, что-то в родительском классе.

        //  ToDo:   Отправить клиенту:
        //          1. Размеры главной выборки матча и умолчательные характеристики для построения пустого поля
        //             (но возможно, это в более раннем событии следует передать) для построения пустой выборки главного поля.
        //          2. Объекты матча статические.
        //          3. Объекты матча динамические. Например:
        //              - для Сапёра: флаги и количество мин на открытых плитках.

        //  Здесь, в таком порядке:
        // 1. super.start(),
        // 2. setGameObjectsPlacement(new MinesweeperPlacementStateAutomaton(...)).

        super.start(gameMatchExtendedDto);
        setGameObjectsPlacementStateAutomaton(new MinesweeperPlacementStateAutomaton(
                this,
                //  ToDo:   Переделать getWidth(), getHeight() в родительском классе.
                //          Пусть там будет переменная WidthHeightSizes.
                new WidthHeightSizes(getWidth(), getHeight()),
                getFromParamsOfModelValueMap(PARAM_NAME_PERCENTS_OF_MINES)
        ));

        setGameMatchExtendedDto(gameMatchExtendedDto);
    }

    // interface IGameMatch
    @Override
    public void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick) {
        MGOStateAutomaton mgoStateAutomaton = (MGOStateAutomaton) getGameObjectsPlacementStateAutomaton()
                //  Найдём объект по координатам
                .getGameObjectStateAutomatonSetFilteredByXYCoordinate(gameCommandMouseClick.getXYCoordinate())
                .stream()
                .findAny()
                .orElse(null);
        assert mgoStateAutomaton != null;
        if (gameCommandMouseClick.getMouseButton() == MouseButton.PRIMARY) {
            //  Откроем объект
            mgoStateAutomaton.open();
        } else if (gameCommandMouseClick.getMouseButton() == MouseButton.SECONDARY) {
            //  Инвертируем флаг объекту
            mgoStateAutomaton.inverseFlag();
        }
    }

    @Override
    public void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed) {
        if (gameCommandKeyPressed.getKeyCode() == KeyCode.ESCAPE) {
            restart();
        }
    }
}
