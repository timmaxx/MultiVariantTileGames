package timmax.minesweeper.jfx.controller;

import org.slf4j.Logger;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.GameStatus;
import timmax.minesweeper.model.MinesweeperModel;
import timmax.tilegameenginejfx.Game;
import timmax.tilegameenginejfx.GameController;

import static org.slf4j.LoggerFactory.getLogger;

public class MinesweeperController extends GameController {
    private static final Logger log = getLogger( MinesweeperController.class);


    public MinesweeperController( BaseModel baseModel, Game game) {
        super( baseModel, game);
    }

    private MinesweeperModel getMinesweeperModel( ) {
        return ( MinesweeperModel)baseModel;
    }

    @Override
    public void onMouseLeftClick( int x, int y) {
        log.debug( "onMouseLeftClick( {}, {})", x, y);
        if ( getMinesweeperModel( ).getGameStatus( ) == GameStatus.GAME) {
            getMinesweeperModel( ).open( x, y);
        } else {
            game.initialize( );
        }
    }

    @Override
    public void onMouseRightClick( int x, int y) {
        log.debug( "onMouseRightClick( {}, {})", x, y);
        getMinesweeperModel( ).inverseFlag( x, y);
    }
}