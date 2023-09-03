package timmax.minesweeper.jfx;

import org.slf4j.Logger;
import timmax.basetilemodel.*;
import timmax.tilegameenginejfx.*;
import timmax.minesweeper.model.*;
import timmax.minesweeper.jfx.controller.MinesweeperController;
import timmax.minesweeper.jfx.view.*;

import static org.slf4j.LoggerFactory.getLogger;

public class MinesweeperGame extends Game {
    private static final Logger log = getLogger( MinesweeperGame.class);
    private static final int REST_OF_MINE_INSTALLATION_IN_PERCENTS = 10;

    private final static int SIDE_OF_WIDTH = 15;
    private final static int SIDE_OF_HEIGHT = 10;

    private MinesweeperModel minesweeperModel;
    private View viewMainArea;
    private View viewGameOverMessage;

    @Override
    public void initialize( ) {
        log.debug( "initialize");
        minesweeperModel = new MinesweeperModel();
        createGame( );
    }

    @Override
    public void createGame( ) {
        log.debug( "createGame");
        minesweeperModel.createNewGame( SIDE_OF_WIDTH, SIDE_OF_HEIGHT, REST_OF_MINE_INSTALLATION_IN_PERCENTS);

        setGameScreenController( new MinesweeperController( minesweeperModel, this));
        viewMainArea = new MinesweeperViewMainArea( minesweeperModel, this);
        viewGameOverMessage = new ViewGameOverMessage( minesweeperModel, this);

        minesweeperModel.notifyViews( );
    }
}