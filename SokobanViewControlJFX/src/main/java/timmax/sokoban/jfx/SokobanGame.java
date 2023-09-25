package timmax.sokoban.jfx;

import timmax.basetilemodel.BaseModel;
import timmax.tilegameenginejfx.*;
import timmax.sokoban.model.*;
import timmax.sokoban.jfx.view.*;
import timmax.sokoban.jfx.controller.SokobanGameSceneController;

public class SokobanGame extends Game {
    @Override
    public BaseModel initModel( ) {
        return new SokobanModel( );
    }

    @Override
    public ViewJfx initViewMainField( BaseModel baseModel, GameStackPaneController gameStackPaneController) {
        return new SokobanMainFieldViewJfx( baseModel, gameStackPaneController);
    }

    @Override
    public String initTitle( ) {
        return "Sokoban";
    }

    @Override
    public GameSceneController initGameSceneController( BaseModel baseModel) {
        return new SokobanGameSceneController( baseModel);
    }

    @Override
    public GameStackPaneController initGameStackPaneController( BaseModel basemodel) {
        return null;
    }
}