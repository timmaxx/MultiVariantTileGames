package timmax.sokoban;

import javafx.scene.input.KeyCode;
import timmax.basetilemodel.GameStatus;
import timmax.basetilemodel.View;
import timmax.sokoban.controller.SokobanController;
import timmax.sokoban.model.*;
import timmax.sokoban.view.*;
import timmax.tilegameenginejfx.*;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class SokobanGame extends Game {
    private static final Logger log = getLogger(SokobanGame.class);

    private SokobanModel sokobanModel;
    private View viewMainArea;
    private View viewGameOverMessage;
    private SokobanController sokobanController;

    @Override
    public void initialize( ) {
        log.debug("");
        sokobanModel = new SokobanModel( );
        createGame( );
    }

    private void createGame( ) {
        log.debug("createGame");
        sokobanModel.createNewGame();

        viewMainArea = new SokobanViewMainArea( sokobanModel, this);
        viewGameOverMessage = new ViewGameOverMessage( sokobanModel, this);

        sokobanController = new SokobanController( sokobanModel);

        sokobanModel.notifyViews();

        sokobanModel.dropCurrentLevelChanged( );
    }

    @Override
    public void onKeyPress( KeyCode keyCode) {
        log.debug("onKeyPress( {})", keyCode);
        if ( sokobanModel.isCurrentLevelChanged( )) {
            createGame( );
        }
        if ( sokobanModel.getGameStatus( ) == GameStatus.GAME) {
            sokobanController.onKeyPress( keyCode);
        }
    }
}