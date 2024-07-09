package timmax.tilegame.game.minesweeper.model;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

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

import static javafx.scene.paint.Color.*;

public class GameMatchOfMinesweeper<ClientId> extends GameMatch<ClientId> {
    public static final String PARAM_NAME_PERCENTS_OF_MINES = "Percents of mines";

    // Константы, описанные ниже относятся к визуализации.
    // ToDo: Вынести логику визуализации из класса.
    public static final Color UNOPENED_CELL_COLOR = ORANGE;
    public static final Color OPENED_CELL_COLOR = GREEN;

    public static final String FLAG = "🚩"; // "\uD83D\uDEA9";
    public static final Color FLAG_CELL_COLOR = YELLOW;

    public static final String MINE = "💣"; // "\uD83D\uDCA3";
    public static final Color MINE_CELL_COLOR = RED;

    private final LevelGenerator levelGenerator = new LevelGenerator();

    private AllMinesweeperObjects<ClientId> allMinesweeperObjects;

    // ToDo: См. комментарии о согласовании параметров в
    //       - GameType :: GameType(...)
    //       и в
    //       - GameMatchLoader :: getCollectionOfGameType(...)
    public GameMatchOfMinesweeper(
            RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton,
            ClientId clientId)
            throws ClassNotFoundException, NoSuchMethodException {
        super(new GameTypeOfMinesweeper(), remoteClientStateAutomaton, clientId);
    }

    public void createNewGame(int width, int height, int percentsOfMines) {
        if (allMinesweeperObjects != null && getGameStatus() == GameStatus.GAME) {
            return;
        }

        // ToDo: Избавиться от "Warning:(56, 33) Unchecked assignment: 'timmax.tilegame.game.minesweeper.model.gameobject.AllMinesweeperObjects' to 'timmax.tilegame.game.minesweeper.model.gameobject.AllMinesweeperObjects<ClientId>'"
        allMinesweeperObjects = levelGenerator.getLevel(width, height, percentsOfMines);
        allMinesweeperObjects.setModel(this);

        sendGameEvent(new GameEventMinesweeperVariableParamsOpenClose(0, width * height));
        sendGameEvent(new GameEventMinesweeperVariableParamsFlag(0, allMinesweeperObjects.getCountOfMines()));
        super.createNewGame(width, height, UNOPENED_CELL_COLOR, BLACK, "");
    }

    private void tryInverseFlag(int x, int y) {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame() ||
                allMinesweeperObjects.getTileByXY(x, y).isOpen()) {
            return;
        }
        allMinesweeperObjects.tryInverseFlag(allMinesweeperObjects.getTileByXY(x, y));
    }

    private void open(int x, int y) {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setGameStatus(allMinesweeperObjects.open(allMinesweeperObjects.getTileByXY(x, y)));
    }

    // interface IGameMatch:
    @Override
    public void createNewGame() {
        createNewGame(
                remoteClientStateAutomaton.getParamsOfModelValueMap().get(PARAM_NAME_WIDTH),
                remoteClientStateAutomaton.getParamsOfModelValueMap().get(PARAM_NAME_HEIGHT),
                remoteClientStateAutomaton.getParamsOfModelValueMap().get(PARAM_NAME_PERCENTS_OF_MINES)
        );
        sendGameEvent(new GameEventMinesweeperPersistentParams(allMinesweeperObjects.getCountOfMines()));
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
