package timmax.sokoban;

import javafx.scene.input.KeyCode;
import timmax.basetilemodel.View;
import timmax.sokoban.controller.Controller;
import timmax.sokoban.model.SokobanModel;
import timmax.sokoban.view.*;
import timmax.tilegameenginejfx.Game;

public class SokobanGame extends Game {
    private SokobanModel sokobanModel;
    private View viewMainArea;
    private Controller controller;

    @Override
    public void initialize( ) {
        sokobanModel = new SokobanModel( );
        createGame( );
    }

    private void createGame( ) {
        sokobanModel.createNewGame();
        viewMainArea = new ViewMainArea( this);
        controller = new Controller( sokobanModel);

        setScreenSize( sokobanModel.getWidth( ), sokobanModel.getHeight( ));
        viewMainArea.setModel( sokobanModel);
        viewMainArea.updateAllTiles( );
        sokobanModel.dropCurrentLevelChanged();
    }

    @Override
    public void onKeyPress( KeyCode keyCode) {
        controller.onKeyPress( keyCode);
        if (sokobanModel.isCurrentLevelChanged()) {
            createGame();
        }
    }
}