package timmax.minesweeper.jfx;

import timmax.basetilemodel.*;
import timmax.minesweeper.jfx.controller.MinesweeperGameStackPaneController;
import timmax.tilegameenginejfx.*;
import timmax.minesweeper.model.*;
import timmax.minesweeper.jfx.view.*;

public class MinesweeperGame extends Game {
    @Override
    public BaseModel initModel( ) {
        return new MinesweeperModel( );
    }

    @Override
    public ViewJfx initViewOfMainField( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        return new MinesweeperMainFieldViewJfx( baseModel, gameStackPaneController);
    }

    @Override
    public String initAppTitle( ) {
        return "Minesweeper";
    }

    @Override
    public GameSceneController initGameSceneController( BaseModel baseModel) {
        return null;
    }

    @Override
    public GameStackPaneController initGameStackPaneController( BaseModel basemodel) {
        return new MinesweeperGameStackPaneController( basemodel);
    }
}