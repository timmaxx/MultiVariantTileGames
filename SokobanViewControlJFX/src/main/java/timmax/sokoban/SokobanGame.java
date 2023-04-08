package timmax.sokoban;

import javafx.scene.input.KeyCode;
import timmax.basetilemodel.View;
import timmax.sokoban.controller.SokobanController;
import timmax.sokoban.model.SokobanModel;
import timmax.sokoban.view.*;
import timmax.tilegameenginejfx.Game;

public class SokobanGame extends Game {
    private SokobanModel sokobanModel;
    private View viewMainArea;
    private SokobanController sokobanController;

    @Override
    public void initialize( ) {
        sokobanModel = new SokobanModel( );
        createGame( );
    }

    private void createGame( ) {
        sokobanModel.createNewGame();
        viewMainArea = new SokobanViewMainArea( this);
        sokobanController = new SokobanController( sokobanModel);

        setScreenSize( sokobanModel.getWidth( ), sokobanModel.getHeight( ));
        viewMainArea.setModel( sokobanModel);
        sokobanModel.notifyViews();

        sokobanModel.dropCurrentLevelChanged( );
    }

    @Override
    public void onKeyPress( KeyCode keyCode) {
        sokobanController.onKeyPress( keyCode);
        if ( sokobanModel.isCurrentLevelChanged( )) {
            createGame( );
        }
    }
}