package timmax.sokoban.jfx;

import timmax.basetilemodel.View;
import timmax.sokoban.model.*;
import timmax.sokoban.jfx.view.*;
import timmax.sokoban.jfx.controller.*;
import timmax.tilegameenginejfx.*;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class SokobanGame extends Game {
    private static final Logger log = getLogger( SokobanGame.class);

    private SokobanModel sokobanModel;
    private View viewMainArea;
    private View viewGameOverMessage;

    @Override
    public void initialize( ) {
        log.debug( "initialize");
        sokobanModel = new SokobanModel( );
        createGame( );
    }

    @Override
    public void createGame( ) {
        log.debug( "createGame");

        sokobanModel.createNewGame( );
        setGameScreenController( new SokobanController( sokobanModel, this));
        viewMainArea = new SokobanViewMainArea( sokobanModel, this);
        viewGameOverMessage = new ViewGameOverMessage( sokobanModel, this);
        sokobanModel.notifyViews( );

        sokobanModel.dropCurrentLevelChanged( ); // Аналога этого вызова нет в Сапёре...
    }
}