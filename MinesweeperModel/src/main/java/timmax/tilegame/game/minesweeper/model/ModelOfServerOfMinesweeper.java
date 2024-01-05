package timmax.tilegame.game.minesweeper.model;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.transport.TransportOfServer;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsFlag;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsOpenClose;
import timmax.tilegame.game.minesweeper.model.gameevent.GameEventOneTileChangeFlag;
import timmax.tilegame.game.minesweeper.model.gameobject.AllMinesweeperObjects;
import timmax.tilegame.game.minesweeper.model.gameobject.LevelGenerator;

import static javafx.scene.paint.Color.*;

public class ModelOfServerOfMinesweeper<T> extends ModelOfServer<T> {
    private final static int REST_OF_MINE_INSTALLATION_IN_PERCENTS = 10;
    private final static int SIDE_OF_WIDTH = 15;
    private final static int SIDE_OF_HEIGHT = 10;

    // Константы, описанные ниже относятся к визуализации.
    // ToDo: Вынести логику визуализации из класса.
    public final static Color UNOPENED_CELL_COLOR = ORANGE;
    public final static Color OPENED_CELL_COLOR = GREEN;

    public final static String FLAG = "🚩"; // "\uD83D\uDEA9";
    public final static Color FLAG_CELL_COLOR = YELLOW;

    public final static String MINE = "💣"; // "\uD83D\uDCA3";
    public final static Color MINE_CELL_COLOR = RED;

    private final LevelGenerator levelGenerator = new LevelGenerator();

    private AllMinesweeperObjects<T> allMinesweeperObjects;

    public ModelOfServerOfMinesweeper(TransportOfServer<T> transportOfServer) {
        super(transportOfServer);
    }

    @Override
    public void createNewGame() {
        createNewGame(SIDE_OF_WIDTH, SIDE_OF_HEIGHT);
        sendGameEvent(new GameEventMinesweeperPersistentParams(allMinesweeperObjects.getCountOfMines()));
    }

    @Override
    public void createNewGame(int width, int height) {
        allMinesweeperObjects = levelGenerator.getLevel(width, height, REST_OF_MINE_INSTALLATION_IN_PERCENTS);
        allMinesweeperObjects.setModel(this);

        sendGameEvent(new GameEventMinesweeperVariableParamsOpenClose(0, width * height));
        sendGameEvent(new GameEventMinesweeperVariableParamsFlag(0, allMinesweeperObjects.getCountOfMines()));
        super.createNewGame(width, height, UNOPENED_CELL_COLOR, BLACK, "");
    }

    private void inverseFlag(int x, int y) {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }

        if (allMinesweeperObjects.getTileByXY(x, y).isOpen()) {
            return;
        }

        try {
            boolean isFlag = allMinesweeperObjects.inverseFlag(allMinesweeperObjects.getTileByXY(x, y));
            sendGameEvent(new GameEventOneTileChangeFlag(x, y, isFlag));
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            System.exit(1);
        }
    }

    private void open(int x, int y) {
        if (verifyGameStatusNotGameAndMayBeCreateNewGame()) {
            return;
        }
        setGameStatus(allMinesweeperObjects.open(allMinesweeperObjects.getTileByXY(x, y)));
    }

    @Override
    public void win() {
    }

    @Override
    public void executeMouseCommand(GameCommandMouseClick gameCommandMouseClick) {
        int x = gameCommandMouseClick.getX();
        int y = gameCommandMouseClick.getY();
        if (gameCommandMouseClick.getMouseButton() == MouseButton.PRIMARY) {
            open(x, y);
        } else if (gameCommandMouseClick.getMouseButton() == MouseButton.SECONDARY) {
            inverseFlag(x, y);
        }
    }
}
