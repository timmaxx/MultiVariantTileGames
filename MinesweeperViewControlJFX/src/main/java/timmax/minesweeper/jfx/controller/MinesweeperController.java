package timmax.minesweeper.jfx.controller;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.GameStatus;
import timmax.minesweeper.model.MinesweeperModel;
import timmax.tilegameenginejfx.Game;
import timmax.tilegameenginejfx.GameController;

public class MinesweeperController extends GameController {
    public MinesweeperController( BaseModel baseModel, Game game) {
        super( baseModel, game);
    }

    private MinesweeperModel getMinesweeperModel( ) {
        return ( MinesweeperModel)baseModel;
    }

    @Override
    public void onMouseLeftClick( int x, int y) {
        if ( getMinesweeperModel( ).getGameStatus( ) != GameStatus.GAME) {
            game.initialize( );
            return;
        }

        getMinesweeperModel( ).open( x, y);
    }

    @Override
    public void onMouseRightClick( int x, int y) {
        getMinesweeperModel( ).inverseFlag( x, y);
    }
}