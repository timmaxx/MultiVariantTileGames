package timmax.tilegame.game.minesweeper.model;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsFlag;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsOpenClose;
import timmax.tilegame.game.minesweeper.model.gameobject.AllMinesweeperObjects;
import timmax.tilegame.game.minesweeper.model.gameobject.LevelGenerator;

import java.util.Map;

public class GameMatchOfMinesweeper<ClientId> extends GameMatch<ClientId> {
    public static final String PARAM_NAME_PERCENTS_OF_MINES = "Percents of mines";

    private final LevelGenerator levelGenerator = new LevelGenerator();

    private AllMinesweeperObjects<ClientId> allMinesweeperObjects;

    // ToDo: См. комментарии о согласовании параметров в
    //       - GameType :: GameType(...)
    //       и в
    //       - GameMatchLoader :: getCollectionOfGameType(...)
    public GameMatchOfMinesweeper(
            RemoteClientStateAutomaton remoteClientStateAutomaton,
            ClientId clientId)
            throws ClassNotFoundException, NoSuchMethodException {
        super(new GameTypeOfMinesweeper(), remoteClientStateAutomaton, clientId);
    }

    public void start(int percentsOfMines) {
        verifyGameMatchIsPlaying();
        if (allMinesweeperObjects != null && getGameStatus() == GameStatus.GAME) {
            return;
        }

        // ToDo: Избавиться от "Warning:(45, 33) Unchecked assignment: 'timmax.tilegame.game.minesweeper.model.gameobject.AllMinesweeperObjects' to 'timmax.tilegame.game.minesweeper.model.gameobject.AllMinesweeperObjects<ClientId>'"
        allMinesweeperObjects = levelGenerator.getLevel(width, height, percentsOfMines);
        allMinesweeperObjects.setModel(this);

        sendGameEventToAllViews(new GameEventMinesweeperVariableParamsOpenClose(0, width * height));
        sendGameEventToAllViews(new GameEventMinesweeperVariableParamsFlag(0, allMinesweeperObjects.getCountOfMines()));

        super.start(width, height, Map.of());
    }

    private void tryInverseFlag(int x, int y) {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame() ||
                allMinesweeperObjects.getTileByXY(x, y).isOpen()) {
            return;
        }
        allMinesweeperObjects.tryInverseFlag(allMinesweeperObjects.getTileByXY(x, y));
        // Т.к. пометка флагом не раскрывает карту и не может привести к проигрышу, то вызов
        // setGameMatchIsPlayingTrue() делать не нужно.
        // Но пока, для отладки, оставлен этот вызов.
        setGameMatchIsPlayingTrue();
    }

    private void open(int x, int y) {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setGameStatus(allMinesweeperObjects.open(allMinesweeperObjects.getTileByXY(x, y)));
        setGameMatchIsPlayingTrue();
    }

    // interface IGameMatch:
    @Override
    public void setParamsOfModelValueMap(Map<String, Integer> paramsOfModelValueMap) {
        verifyGameMatchIsPlaying();
        if (getGameStatus() == GameStatus.GAME) {
            throw new RuntimeException("Wrong situation: getGameStatus() == GameStatus.GAME");
        }

        super.setParamsOfModelValueMap(paramsOfModelValueMap);

        // ToDo: Избавиться от "Warning:(69, 33) Unchecked assignment: 'timmax.tilegame.game.minesweeper.model.gameobject.AllMinesweeperObjects' to 'timmax.tilegame.game.minesweeper.model.gameobject.AllMinesweeperObjects<ClientId>'"
        allMinesweeperObjects = levelGenerator.getLevel(getWidth(), getHeight(), paramsOfModelValueMap.get(PARAM_NAME_PERCENTS_OF_MINES));
        allMinesweeperObjects.setModel(this);
    }

    @Override
    public void start() {
        verifyGameMatchIsPlaying();
        if (allMinesweeperObjects != null && getGameStatus() == GameStatus.GAME) {
            return;
        }

        sendGameEventToAllViews(new GameEventMinesweeperVariableParamsOpenClose(0, getWidth() * getHeight()));
        sendGameEventToAllViews(new GameEventMinesweeperVariableParamsFlag(0, allMinesweeperObjects.getCountOfMines()));

        super.start();
    }

    @Override
    public void resume() {
        // ToDo: Что-то из описанного ниже ToDo сделать здесь, что-то в родительском классе.
        // ToDo: Отправить клиенту:
        //       1. Размеры главной выборки матча и умолчательные характеристики для построение пустого поля
        //          (но возможно, это в более раннем событии следует передать) для построения пустой выборки главного поля.
        //       2. Объекты матча статические (например для Сокобана: стены или дома).
        //       3. Объекты матча динамические. Например:
        //          - для Сокобана: игрок, ящики.
        //          - для Сапёра: флаги и количество мин на открытых плитках.
    }

    @Override
    public void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick) {
        int x = gameCommandMouseClick.getX();
        int y = gameCommandMouseClick.getY();
        if (gameCommandMouseClick.getMouseButton() == MouseButton.PRIMARY) {
            open(x, y);
        } else if (gameCommandMouseClick.getMouseButton() == MouseButton.SECONDARY) {
            tryInverseFlag(x, y);
        }
    }

    @Override
    public void executeKeyboardCommand(GameCommandKeyPressed gameCommandKeyPressed) {
        if (gameCommandKeyPressed.getKeyCode() == KeyCode.ESCAPE) {
            restart();
        }
    }
}
