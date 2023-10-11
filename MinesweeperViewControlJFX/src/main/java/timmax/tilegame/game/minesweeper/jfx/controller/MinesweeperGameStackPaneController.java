package timmax.tilegame.game.minesweeper.jfx.controller;

import timmax.tilegame.basemodel.BaseModel;

import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

import timmax.tilegame.game.minesweeper.model.gamecommand.GameCommandMinesweeperInverseFlag;
import timmax.tilegame.game.minesweeper.model.gamecommand.GameCommandMinesweeperOpen;

public class MinesweeperGameStackPaneController extends GameStackPaneController {
    public MinesweeperGameStackPaneController( BaseModel baseModel) {
        super( baseModel);
    }

    @Override
    public void onMousePrimaryClick( int x, int y) {
        gameCommandQueueOfController.add( new GameCommandMinesweeperOpen( x, y));
    }

    @Override
    public void onMouseSecondaryClick( int x, int y) {
        gameCommandQueueOfController.add( new GameCommandMinesweeperInverseFlag( x, y));
    }
}