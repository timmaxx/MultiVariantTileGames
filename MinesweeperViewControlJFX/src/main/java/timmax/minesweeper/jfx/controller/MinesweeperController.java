package timmax.minesweeper.jfx.controller;

import timmax.basetilemodel.*;
import timmax.minesweeper.model.MinesweeperModel;
import timmax.tilegameenginejfx.*;

public class MinesweeperController extends GameController {
    // ToDo: 1. game.initialize( ) перенести в модель и тогда см. п. 2.
    // ToDo: 2. game убрать из контроллера!
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