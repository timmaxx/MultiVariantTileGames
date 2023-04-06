package timmax.sokoban;

import javafx.scene.input.KeyCode;
import timmax.basetilemodel.View;
import timmax.sokoban.controller.Controller;
import timmax.sokoban.model.Model;
import timmax.sokoban.view.*;
import timmax.tilegameenginejfx.Game;

public class SokobanGame extends Game {
    private Model model;
    private View viewMainArea;
    private Controller controller;

    @Override
    public void initialize() {
        createGame( );
    }

    private void createGame( ) {
        model = new Model( 0);
        viewMainArea = new ViewMainArea( this);
        controller = new Controller( model);

        setScreenSize( model.getWidth( ), model.getHeight( ));
        viewMainArea.setModel( model);
        viewMainArea.updateAllTiles( );
    }

    @Override
    public void onKeyPress( KeyCode keyCode) {
        controller.onKeyPress( keyCode);
    }
}