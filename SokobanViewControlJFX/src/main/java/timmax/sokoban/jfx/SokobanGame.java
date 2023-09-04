package timmax.sokoban.jfx;

import timmax.basetilemodel.BaseModel;
import timmax.sokoban.model.*;
import timmax.sokoban.jfx.view.*;
import timmax.sokoban.jfx.controller.*;
import timmax.tilegameenginejfx.*;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class SokobanGame extends Game {
    private static final Logger log = getLogger( SokobanGame.class);

    @Override
    public BaseModel initModel( ) {
        return new SokobanModel( );
    }

    @Override
    public void initialize( ) {
        log.debug( "initialize");

        SokobanModel sokobanModel = ( ( SokobanModel)getModel( ));
        sokobanModel.createNewGame( );
        setGameScreenController( new SokobanController( sokobanModel, this));
        new SokobanViewMainArea( sokobanModel, this);
        new ViewGameOverMessage( sokobanModel, this);
        sokobanModel.notifyViews( );

        sokobanModel.dropCurrentLevelChanged( ); // Аналога этого вызова нет в Сапёре...
    }
}