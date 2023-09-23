package timmax.sokoban.jfx;

import timmax.basetilemodel.BaseModel;
import timmax.sokoban.model.*;
import timmax.sokoban.jfx.view.*;
// import timmax.sokoban.jfx.controller.*;
import timmax.tilegameenginejfx.*;

public class SokobanGame extends Game {
    @Override
    public BaseModel initModel( ) {
        return new SokobanModel( );
    }
/*
    @Override
    public GameController initGameController( BaseModel baseModel, Game game) {
        return new SokobanController( baseModel, game);
    }
*/
    @Override
    public ViewJfx initViewMainField( BaseModel baseModel) {
        return new SokobanMainFieldViewJfx( baseModel);
    }

    @Override
    public String initTitle( ) {
        return "Sokoban";
    }
}