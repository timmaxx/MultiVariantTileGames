package timmax.sokoban.controller;

import timmax.sokoban.model.SokobanModel;
import javafx.scene.input.KeyCode;

import static timmax.basetilemodel.Direction.*;

public class SokobanController {
    private final SokobanModel baseSokobanModel;

    public SokobanController( SokobanModel baseSokobanModel) {
        this.baseSokobanModel = baseSokobanModel;
    }

    public void onKeyPress( KeyCode keyCode) {
        if        ( keyCode.equals(KeyCode.Q)) { baseSokobanModel.moveUndo( );
        } else if ( keyCode.equals(KeyCode.P)) { baseSokobanModel.moveRedo( );
        } else if ( keyCode.equals(KeyCode.LEFT)) { baseSokobanModel.move( LEFT);
        } else if ( keyCode.equals(KeyCode.RIGHT)) { baseSokobanModel.move( RIGHT);
        } else if ( keyCode.equals(KeyCode.UP)) { baseSokobanModel.move( UP);
        } else if ( keyCode.equals(KeyCode.DOWN)) { baseSokobanModel.move( DOWN);
        } else if ( keyCode.equals(KeyCode.BACK_SPACE)) { baseSokobanModel.decLevel( );
        } else if ( keyCode.equals(KeyCode.SPACE)) { baseSokobanModel.incLevel( );
        } else if ( keyCode.equals(KeyCode.ESCAPE)) { baseSokobanModel.restart( );
        }
    }
}