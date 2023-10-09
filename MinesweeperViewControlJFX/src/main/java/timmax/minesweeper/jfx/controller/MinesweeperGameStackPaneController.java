package timmax.minesweeper.jfx.controller;

import timmax.basetilemodel.*;
import timmax.tilegame.basemodel.ConnectionToBaseModel;
import timmax.tilegame.basemodel.GameStatus;
import timmax.tilegameenginejfx.GameStackPaneController;

public class MinesweeperGameStackPaneController extends GameStackPaneController {
    // public MinesweeperGameStackPaneController( BaseModel baseModel)
    public MinesweeperGameStackPaneController( ConnectionToBaseModel connectionToBaseModel) {
        // super( baseModel);
        super( connectionToBaseModel);
    }

/*
    @Override
    protected void onMousePrimaryClick( int x, int y) {
        if ( getMinesweeperModel( ).getGameStatus( ) != GameStatus.GAME) {
            getMinesweeperModel( ).createNewGame( );
            return;
        }
        getMinesweeperModel( ).open( x, y);
    }
*/
    @Override
    protected void onMousePrimaryClick( int x, int y) {
        if ( getConnectionToBaseModel( ).getGameStatus( ) != GameStatus.GAME) {
            getConnectionToBaseModel( ).createNewGame( );
            return;
        }
        getConnectionToBaseModel( ).open( x, y);
    }

/*
    @Override
    protected void onMouseSecondaryClick( int x, int y) {
        getMinesweeperModel( ).inverseFlag( x, y);
    }
*/
    @Override
    protected void onMouseSecondaryClick( int x, int y) {
        getConnectionToBaseModel( ).inverseFlag( x, y);
    }

/*
    private MinesweeperModel getMinesweeperModel( ) {
        return ( MinesweeperModel)baseModel;
    }
*/
    private ConnectionToBaseModel getConnectionToBaseModel( ) {
        return ( ConnectionToBaseModel)connectionToBaseModel;
    }
}