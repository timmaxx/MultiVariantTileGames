package timmax.sokoban.jfx.controller;

import org.slf4j.Logger;
import javafx.scene.input.KeyCode;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.GameStatus;
import timmax.sokoban.model.SokobanModel;
import timmax.tilegameenginejfx.Game;
import timmax.tilegameenginejfx.GameScreenController;

import static org.slf4j.LoggerFactory.getLogger;
import static timmax.basetilemodel.tile.Direction.*;

public class SokobanController implements GameScreenController {
    private static final Logger log = getLogger( SokobanController.class);

    private final SokobanModel baseSokobanModel;
    private final Game game;

    public SokobanController( BaseModel baseModel, Game game) {
        this.game = game;
        this.baseSokobanModel = ( SokobanModel)baseModel;
    }

    @Override
    public void onMouseLeftClick( int x, int y) {
    }

    @Override
    public void onMouseRightClick( int x, int y) {
    }

    public void onKeyPress( KeyCode keyCode) {
        log.debug( "onKeyPress( {})", keyCode);

        if ( baseSokobanModel.isCurrentLevelChanged( )) {
            game.initialize( );
            return;
        }
        if ( baseSokobanModel.getGameStatus( ) != GameStatus.GAME) {
            return;
        }
        if        ( keyCode.equals( KeyCode.Q)) { baseSokobanModel.moveUndo( );
        } else if ( keyCode.equals( KeyCode.P)) { baseSokobanModel.moveRedo( );
        } else if ( keyCode.equals( KeyCode.LEFT)) { baseSokobanModel.move( LEFT);
        } else if ( keyCode.equals( KeyCode.RIGHT)) { baseSokobanModel.move( RIGHT);
        } else if ( keyCode.equals( KeyCode.UP)) { baseSokobanModel.move( UP);
        } else if ( keyCode.equals( KeyCode.DOWN)) { baseSokobanModel.move( DOWN);
        } else if ( keyCode.equals( KeyCode.BACK_SPACE)) { baseSokobanModel.decLevel( );
        } else if ( keyCode.equals( KeyCode.SPACE)) { baseSokobanModel.incLevel( );
        } else if ( keyCode.equals( KeyCode.ESCAPE)) { baseSokobanModel.restart( );
        }
    }

    @Override
    public void onKeyReleased( KeyCode keyCode) {
    }
}