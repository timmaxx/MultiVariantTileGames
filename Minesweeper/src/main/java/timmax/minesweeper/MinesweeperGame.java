package timmax.minesweeper;

import timmax.tilegameengine.*;
import timmax.minesweeper.gamefield.MinesweeperField;

public class MinesweeperGame extends Game {
    private static  int SIDE_OF_WIDTH = 2;
    private static  int SIDE_OF_HEIGHT = 2;

    private static final int REST_OF_MINE_INSTALLATION_IN_PERCENTS = 10;

    private MinesweeperField minesweeperField;

    @Override
    public void initialize() {
        // setScreenSize(SIDE_OF_WIDTH, SIDE_OF_HEIGHT);
        createGame();
    }

    private void createGame() {
        SIDE_OF_WIDTH++;
        SIDE_OF_HEIGHT++;
        setScreenSize(SIDE_OF_WIDTH, SIDE_OF_HEIGHT);
        // reCreateContent();
        minesweeperField = new MinesweeperField(this, SIDE_OF_WIDTH, SIDE_OF_HEIGHT, REST_OF_MINE_INSTALLATION_IN_PERCENTS);
        minesweeperField.showBegin();
        // showCoordinates(true);
        // showGrid(false);
    }

    @Override
    public void onMouseLeftClick( int x, int y) {
        if ( minesweeperField.isGameStopped( )) {
            createGame( );
        } else {
            minesweeperField.openTile( x, y);
            // setLives( 123);
        }
    }

    @Override
    public void onMouseRightClick( int x, int y) {
        minesweeperField.markTile( x, y);
    }
}
