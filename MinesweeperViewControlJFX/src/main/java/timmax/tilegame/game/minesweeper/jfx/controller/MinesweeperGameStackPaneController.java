package timmax.tilegame.game.minesweeper.jfx.controller;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.GameStatus;

import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

import timmax.tilegame.game.minesweeper.model.MinesweeperModel;

public class MinesweeperGameStackPaneController extends GameStackPaneController {
    public MinesweeperGameStackPaneController( BaseModel baseModel) {
        super( baseModel);
    }

    @Override
    public void onMousePrimaryClick( int x, int y) {
        // ToDo: Такое условие не хорошо иметь в контроллере, т.к. контроллер пытается запросить состояние игры.
        //  А этим должны заниматься выборки, а не контроллер! Убрать в модель.
        // Например, здесь в отдельный метод, который вызывать при каждом поступлении любой команды.
        if ( getMinesweeperModel( ).getGameStatus( ) != GameStatus.GAME) {
            getMinesweeperModel( ).createNewGame( );
            return;
        }

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