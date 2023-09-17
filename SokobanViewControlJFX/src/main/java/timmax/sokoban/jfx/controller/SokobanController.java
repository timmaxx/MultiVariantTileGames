package timmax.sokoban.jfx.controller;

import javafx.scene.input.KeyCode;
import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.GameStatus;
import timmax.sokoban.model.SokobanModel;
import timmax.tilegameenginejfx.Game;
import timmax.tilegameenginejfx.GameController;

import static timmax.basetilemodel.tile.Direction.*;

public class SokobanController extends GameController {
    public SokobanController( BaseModel baseModel, Game game) {
        super( baseModel, game);
    }

    private SokobanModel getSokobanModel( ) {
        return ( SokobanModel)baseModel;
    }

    public void onKeyPress( KeyCode keyCode) {
        if ( getSokobanModel( ).getGameStatus( ) != GameStatus.GAME) {
            game.initialize( );
            return;
        }

        if        ( keyCode.equals( KeyCode.Q))          { getSokobanModel( ).moveUndo( );
        } else if ( keyCode.equals( KeyCode.P))          { getSokobanModel( ).moveRedo( );
        } else if ( keyCode.equals( KeyCode.LEFT))       { getSokobanModel( ).move( LEFT);
        } else if ( keyCode.equals( KeyCode.RIGHT))      { getSokobanModel( ).move( RIGHT);
        } else if ( keyCode.equals( KeyCode.UP))         { getSokobanModel( ).move( UP);
        } else if ( keyCode.equals( KeyCode.DOWN))       { getSokobanModel( ).move( DOWN);
        } else if ( keyCode.equals( KeyCode.BACK_SPACE)) { getSokobanModel( ).decLevel( );
        } else if ( keyCode.equals( KeyCode.SPACE))      { getSokobanModel( ).incLevel( );
        } else if ( keyCode.equals( KeyCode.ESCAPE))     { getSokobanModel( ).restart( );
        }
    }
}