package timmax.sokoban.jfx;

import javafx.scene.layout.Pane;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.ViewMainArea;
import timmax.sokoban.model.*;
import timmax.sokoban.jfx.view.*;
import timmax.sokoban.jfx.controller.*;
import timmax.tilegameenginejfx.*;

public class SokobanGame extends Game {
    @Override
    public BaseModel initModel( ) {
        return new SokobanModel( );
    }

    @Override
    public GameController initGameController( BaseModel baseModel, Game game) {
        return new SokobanController( baseModel, game);
    }

    @Override
    public ViewMainArea initViewMainArea( BaseModel baseModel, Pane root) {
        return new SokobanViewMainArea( baseModel, root);
    }

    @Override
    public String initTitle( ) {
        return "Sokoban";
    }
}