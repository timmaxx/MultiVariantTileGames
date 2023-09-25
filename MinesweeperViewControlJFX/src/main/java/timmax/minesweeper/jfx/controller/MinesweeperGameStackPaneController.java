package timmax.minesweeper.jfx.controller;

import timmax.basetilemodel.*;
import timmax.minesweeper.model.MinesweeperModel;
import timmax.tilegameenginejfx.GameStackPaneController;

public class MinesweeperGameStackPaneController extends GameStackPaneController {
    public MinesweeperGameStackPaneController( BaseModel baseModel) {
        super( baseModel);
    }

    @Override
    protected void onMousePrimaryClick( int x, int y) {
        if ( getMinesweeperModel( ).getGameStatus( ) != GameStatus.GAME) {
            getMinesweeperModel( ).createNewGame( );
            return;
        }

        getMinesweeperModel( ).open( x, y);
    }

    @Override
    protected void onMouseSecondaryClick( int x, int y) {
        getMinesweeperModel( ).inverseFlag( x, y);
    }

    private MinesweeperModel getMinesweeperModel( ) {
        return ( MinesweeperModel)baseModel;
    }
}