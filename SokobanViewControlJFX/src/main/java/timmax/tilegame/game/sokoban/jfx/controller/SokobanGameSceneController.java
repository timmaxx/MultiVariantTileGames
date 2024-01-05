package timmax.tilegame.game.sokoban.jfx.controller;

import javafx.scene.input.KeyCode;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.guiengine.jfx.controller.GameSceneController;

// import timmax.tilegame.game.sokoban.model.gamecommand.*;

// import static timmax.tilegame.basemodel.tile.Direction.*;

public class SokobanGameSceneController extends GameSceneController {
    public SokobanGameSceneController(TransportOfClient transportOfClient) {
        super(transportOfClient);
    }

    @Override
    public void onKeyPressed(KeyCode keyCode) {
        GameCommand gameCommand = null;
        /*
        if        ( keyCode.equals( KeyCode.Q))          { gameCommand = new GameCommandSokobanUndo( );
        } else if ( keyCode.equals( KeyCode.P))          { gameCommand = new GameCommandSokobanRedo( );
        } else if ( keyCode.equals( KeyCode.LEFT))       { gameCommand = new GameCommandSokobanMove( LEFT);
        } else if ( keyCode.equals( KeyCode.RIGHT))      { gameCommand = new GameCommandSokobanMove( RIGHT);
        } else if ( keyCode.equals( KeyCode.UP))         { gameCommand = new GameCommandSokobanMove( UP);
        } else if ( keyCode.equals( KeyCode.DOWN))       { gameCommand = new GameCommandSokobanMove( DOWN);
        } else if ( keyCode.equals( KeyCode.BACK_SPACE)) { gameCommand = new GameCommandSokobanPrevLevel( );
        } else if ( keyCode.equals( KeyCode.SPACE))      { gameCommand = new GameCommandSokobanNextLevel( );
        } else if ( keyCode.equals( KeyCode.ESCAPE))     { gameCommand = new GameCommandSokobanRestart( );
        }
        */
        if (gameCommand == null) {
            return;
        }
        sendCommand(gameCommand);
    }
}
