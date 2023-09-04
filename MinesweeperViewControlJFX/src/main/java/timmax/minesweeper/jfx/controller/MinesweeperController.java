package timmax.minesweeper.jfx.controller;

import javafx.scene.input.KeyCode;
import org.slf4j.Logger;
import timmax.basetilemodel.GameStatus;
import timmax.minesweeper.model.MinesweeperModel;
import timmax.tilegameenginejfx.Game;
import timmax.tilegameenginejfx.GameScreenController;

import static org.slf4j.LoggerFactory.getLogger;

public class MinesweeperController implements GameScreenController {
    private static final Logger log = getLogger( MinesweeperController.class);

    private final MinesweeperModel minesweeperModel;
    private final Game game;


    public MinesweeperController( MinesweeperModel minesweeperModel, Game game) {
        this.game = game;
        this.minesweeperModel = minesweeperModel;
    }

    @Override
    public void onMouseLeftClick( int x, int y) {
        log.debug( "onMouseLeftClick( {}, {})", x, y);
        if ( minesweeperModel.getGameStatus( ) == GameStatus.GAME) {
            minesweeperModel.open( x, y);
        } else {
            game.createGame( );
        }
    }

    @Override
    public void onMouseRightClick( int x, int y) {
        log.debug( "onMouseRightClick( {}, {})", x, y);
        minesweeperModel.inverseFlag( x, y);
    }

    @Override
    public void onKeyPress( KeyCode keyCode) {
    }

    @Override
    public void onKeyReleased( KeyCode keyCode) {
    }
}