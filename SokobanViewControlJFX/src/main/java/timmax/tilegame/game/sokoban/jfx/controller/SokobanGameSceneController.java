package timmax.tilegame.game.sokoban.jfx.controller;

import javafx.scene.input.KeyCode;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.GameStatus;

import timmax.tilegame.guiengine.jfx.controller.GameSceneController;

import timmax.tilegame.game.sokoban.model.SokobanModel;

import static timmax.tilegame.basemodel.tile.Direction.*;

public class SokobanGameSceneController extends GameSceneController {
    public SokobanGameSceneController( BaseModel baseModel) {
        super( baseModel);
    }

    @Override
    public void onKeyPressed( KeyCode keyCode) {
        if ( getSokobanModel( ).getGameStatus( ) != GameStatus.GAME) {
            getSokobanModel( ).createNewGame( );
            return;
        }

        if        ( keyCode.equals( KeyCode.Q))          { getSokobanModel( ).moveUndo( );
        } else if ( keyCode.equals( KeyCode.P))          { getSokobanModel( ).moveRedo( );
        } else if ( keyCode.equals( KeyCode.LEFT))       { getSokobanModel( ).move( LEFT);
        } else if ( keyCode.equals( KeyCode.RIGHT))      { getSokobanModel( ).move( RIGHT);
        } else if ( keyCode.equals( KeyCode.UP))         { getSokobanModel( ).move( UP);
        } else if ( keyCode.equals( KeyCode.DOWN))       { getSokobanModel( ).move( DOWN);
        } else if ( keyCode.equals( KeyCode.BACK_SPACE)) { getSokobanModel( ).prevLevel( );
        } else if ( keyCode.equals( KeyCode.SPACE))      { getSokobanModel( ).nextLevel( );
        } else if ( keyCode.equals( KeyCode.ESCAPE))     { getSokobanModel( ).restart( );
        }
    }

    private SokobanModel getSokobanModel( ) {
        return ( SokobanModel)baseModel;
    }
}