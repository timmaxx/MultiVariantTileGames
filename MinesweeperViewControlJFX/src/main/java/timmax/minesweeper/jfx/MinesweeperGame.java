package timmax.minesweeper.jfx;

import org.slf4j.Logger;
import timmax.tilegameenginejfx.*;
import timmax.minesweeper.jfx.controller.MinesweeperController;
import timmax.minesweeper.model.*;
import timmax.minesweeper.jfx.view.*;
import timmax.basetilemodel.*;

import static org.slf4j.LoggerFactory.getLogger;

public class MinesweeperGame extends Game {
    private static final Logger log = getLogger(MinesweeperGame.class);

    private final static int SIDE_OF_WIDTH = 15;
    private final static int SIDE_OF_HEIGHT = 10;

    private static final int REST_OF_MINE_INSTALLATION_IN_PERCENTS = 10;

    private MinesweeperModel minesweeperModel;

    private View viewMainArea;
    private View viewGameOverMessage;

    private MinesweeperController minesweeperController;

    @Override
    public void initialize( ) {
        log.debug("initialize");

        minesweeperModel = new MinesweeperModel();
        createGame( );
    }

    private void createGame( ) {
        log.debug("createGame");
        minesweeperModel.createNewGame( SIDE_OF_WIDTH, SIDE_OF_HEIGHT, REST_OF_MINE_INSTALLATION_IN_PERCENTS);

        viewMainArea = new MinesweeperViewMainArea( minesweeperModel, this);
        viewGameOverMessage = new ViewGameOverMessage( minesweeperModel, this);

        minesweeperController = new MinesweeperController( minesweeperModel);

        minesweeperModel.notifyViews( );
    }

    @Override
    public void onMouseLeftClick( int x, int y) {
        log.debug("onMouseLeftClick( {}, {})", x, y);
        if ( minesweeperModel.getGameStatus( ) == GameStatus.GAME) {
            minesweeperController.onMouseLeftClick( x, y);
        } else {
            createGame( );
        }
    }

    @Override
    public void onMouseRightClick( int x, int y) {
        log.debug("onMouseRightClick( {}, {})", x, y);
        minesweeperController.onMouseRightClick( x, y);
    }
}