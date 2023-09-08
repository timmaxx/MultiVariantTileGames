package timmax.sokoban.jfx;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.ViewMainArea;
import timmax.sokoban.model.*;
import timmax.sokoban.jfx.view.*;
import timmax.sokoban.jfx.controller.*;
import timmax.tilegameenginejfx.*;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class SokobanGame extends Game {
    private static final Logger log = getLogger( SokobanGame.class);

    @Override
    public void initialize( ) {
        log.debug( "initialize");
        super.initialize( );
        ( ( SokobanModel)getModel( )).dropCurrentLevelChanged( ); // Аналога этого вызова нет в Сапёре...
    }

    @Override
    public BaseModel initModel( ) {
        return new SokobanModel( );
    }

    @Override
    public GameScreenController initGameScreenController( BaseModel baseModel, Game game) {
        return new SokobanController( baseModel, game);
    }

    @Override
    public ViewMainArea initViewMainArea( BaseModel baseModel, Game game) {
        return new SokobanViewMainArea( baseModel, game);
    }

    @Override
    public String initTitle( ) {
        return "Sokoban";
    }
}