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
/*
    private static final int REST_OF_MINE_INSTALLATION_IN_PERCENTS = 10;
    private final static int SIDE_OF_WIDTH = 15;
    private final static int SIDE_OF_HEIGHT = 10;
*/

    @Override
    public BaseModel initModel( ) {
        return new MinesweeperModel( );
    }

    @Override
    public void initialize( ) {
        log.debug( "initialize");

        MinesweeperModel minesweeperModel = ( ( MinesweeperModel)getModel( ));

        //minesweeperModel.createNewGame( SIDE_OF_WIDTH, SIDE_OF_HEIGHT, REST_OF_MINE_INSTALLATION_IN_PERCENTS);
        minesweeperModel.createNewGame( );
        setGameScreenController( new MinesweeperController( minesweeperModel, this));
        new MinesweeperViewMainArea( minesweeperModel, this);
        new ViewGameOverMessage( minesweeperModel, this);

        minesweeperModel.notifyViews( );
    }
}