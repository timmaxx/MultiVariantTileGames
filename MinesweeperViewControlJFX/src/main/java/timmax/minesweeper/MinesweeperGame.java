package timmax.minesweeper;

import timmax.basetilemodel.GameStatus;
import timmax.tilegameenginejfx.*;
import timmax.minesweeper.controller.MinesweeperController;
import timmax.minesweeper.model.*;
import timmax.minesweeper.view.*;
import timmax.basetilemodel.View;

public class MinesweeperGame extends Game {
    private final static int SIDE_OF_WIDTH = 10;
    private final static int SIDE_OF_HEIGHT = 10;

    private static final int REST_OF_MINE_INSTALLATION_IN_PERCENTS = 20;

    private MinesweeperModel minesweeperModel;

    private View viewMainArea;
    private View viewGameOverMessage;

    private MinesweeperController minesweeperController;

    @Override
    public void initialize( ) {
        minesweeperModel = new MinesweeperModel( );
        createGame( );
    }

    private void createGame( ) {
        minesweeperModel.createNewGame( SIDE_OF_WIDTH, SIDE_OF_HEIGHT, REST_OF_MINE_INSTALLATION_IN_PERCENTS);
        viewMainArea = new MinesweeperViewMainArea( this);
        viewGameOverMessage = new MinesweeperViewGameOverMessage( this);

        minesweeperController = new MinesweeperController( minesweeperModel);

        setScreenSize( SIDE_OF_WIDTH, SIDE_OF_HEIGHT);
        viewMainArea.setModel( minesweeperModel);
        viewGameOverMessage.setModel( minesweeperModel);
        minesweeperModel.notifyViews( );
    }

    @Override
    public void onMouseLeftClick( int x, int y) {
        if ( minesweeperModel.getGameStatus( ) == GameStatus.GAME) {
            minesweeperController.onMouseLeftClick( x, y);
        } else {
            createGame( );
        }
    }

    @Override
    public void onMouseRightClick( int x, int y) {
        minesweeperController.onMouseRightClick( x, y);
    }
}