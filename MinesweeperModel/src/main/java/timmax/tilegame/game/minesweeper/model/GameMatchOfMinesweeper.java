package timmax.tilegame.game.minesweeper.model;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.gameobject.OneTileGameObjectStateAutomaton;
import timmax.tilegame.basemodel.gameobject.WidthHeightSizes;
import timmax.tilegame.basemodel.gameobject.XYCoordinate;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

import timmax.tilegame.basemodel.protocol.server_client.GameMatchExtendedDto;
import timmax.tilegame.game.minesweeper.model.gameobject.LevelGenerator;
import timmax.tilegame.game.minesweeper.model.gameobject.MinesweeperGameObjectStateAutomaton;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameMatchOfMinesweeper<ClientId> extends GameMatch<ClientId> {
    public static final String PARAM_NAME_PERCENTS_OF_MINES = "Percents of mines";

    private final LevelGenerator levelGenerator; // = new LevelGenerator((GameTypeOfMinesweeper) gameType);

    // ToDo: См. комментарии о согласовании параметров в
    //       - GameType :: GameType(...)
    //       и в
    //       - GameMatchLoader :: getCollectionOfGameType(...)
    public GameMatchOfMinesweeper(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton)
            throws ClassNotFoundException, NoSuchMethodException {
        super(new GameTypeOfMinesweeper(), remoteClientStateAutomaton);

        levelGenerator = new LevelGenerator((GameTypeOfMinesweeper) gameType);
    }

    // interface IGameMatchX:
    @Override
    public void setParamsOfModelValueMap(Map<String, Integer> paramsOfModelValueMap) {
        throwExceptionIfIsPlaying();

        super.setParamsOfModelValueMap(paramsOfModelValueMap);
        oneTileGameObjectsPlacement = levelGenerator.getLevel(
                //  ToDo:   Переделать getWidth(), getHeight() в родительском классе.
                //          Пусть там будет переменная WidthHeightSizes.
                new WidthHeightSizes(getWidth(), getHeight()),
                paramsOfModelValueMap.get(PARAM_NAME_PERCENTS_OF_MINES)
        );
        oneTileGameObjectsPlacement.setGameMatch(this);
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

        super.start(gameMatchExtendedDto);
        oneTileGameObjectsPlacement = levelGenerator.getLevel(
                //  ToDo:   Переделать getWidth(), getHeight() в родительском классе.
                //          Пусть там будет переменная WidthHeightSizes.
                new WidthHeightSizes(getWidth(), getHeight()),
                getFromParamsOfModelValueMap(PARAM_NAME_PERCENTS_OF_MINES)
        );
        oneTileGameObjectsPlacement.setGameMatch(this);

        return newGameMatchExtendedDto(new HashSet<>());
    }

    // interface IGameMatch
    @Override
    public void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick) {
        int x = gameCommandMouseClick.getX();
        int y = gameCommandMouseClick.getY();
        XYCoordinate xyCoordinate = new XYCoordinate(x, y);
        //  Найдём объект по координатам
        Set<OneTileGameObjectStateAutomaton> oneTileGameObjectStateAutomatonSet =
                oneTileGameObjectsPlacement
                        .getOneTileGameObjectStateAutomatonSetInXYCoordinate(xyCoordinate);
        OneTileGameObjectStateAutomaton oneTileGameObjectStateAutomaton = oneTileGameObjectStateAutomatonSet
                .stream()
                .findFirst()
                .orElse(null);
        MinesweeperGameObjectStateAutomaton minesweeperGameObjectStateAutomaton =
                (MinesweeperGameObjectStateAutomaton) oneTileGameObjectStateAutomaton;
        if (gameCommandMouseClick.getMouseButton() == MouseButton.PRIMARY) {
            //  Откроем объект
            minesweeperGameObjectStateAutomaton.open();
        } else if (gameCommandMouseClick.getMouseButton() == MouseButton.SECONDARY) {
            //  Инвертируем флаг объекту
            minesweeperGameObjectStateAutomaton.inverseFlag();
        }
    }

    @Override
    public void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed) {
        if (gameCommandKeyPressed.getKeyCode() == KeyCode.ESCAPE) {
            restart();
        }
    }
}
