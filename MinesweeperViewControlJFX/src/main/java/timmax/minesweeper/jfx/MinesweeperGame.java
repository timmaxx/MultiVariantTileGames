package timmax.minesweeper.jfx;

import timmax.basetilemodel.*;
import timmax.tilegameenginejfx.*;
import timmax.minesweeper.model.*;
// import timmax.minesweeper.jfx.controller.MinesweeperController;
import timmax.minesweeper.jfx.view.*;

public class MinesweeperGame extends Game {
    @Override
    public BaseModel initModel( ) {
        return new MinesweeperModel( );
    }
/*
    @Override
    public GameController initGameController( BaseModel baseModel, Game game) {
        return new MinesweeperController( baseModel, game);
    }
*/
    @Override
    public ViewJfx initViewMainField( BaseModel baseModel, Game game) {
        return new MinesweeperMainFieldViewJfx( baseModel, game);
    }

    @Override
    public String initTitle( ) {
        return "Minesweeper";
    }
}