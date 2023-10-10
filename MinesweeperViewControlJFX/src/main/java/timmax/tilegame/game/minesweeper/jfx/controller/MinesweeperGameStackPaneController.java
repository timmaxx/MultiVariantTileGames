package timmax.tilegame.game.minesweeper.jfx.controller;

import timmax.tilegame.basemodel.BaseModel;

import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

import timmax.tilegame.game.minesweeper.model.MinesweeperModel;

public class MinesweeperGameStackPaneController extends GameStackPaneController {
    public MinesweeperGameStackPaneController( BaseModel baseModel) {
        super( baseModel);
    }

    @Override
    public void onMousePrimaryClick( int x, int y) {
        getMinesweeperModel( ).open( x, y);
    }

    @Override
    public void onMouseSecondaryClick( int x, int y) {
        getMinesweeperModel( ).inverseFlag( x, y);
    }

    private MinesweeperModel getMinesweeperModel( ) {
        return ( MinesweeperModel)baseModel;
    }
}