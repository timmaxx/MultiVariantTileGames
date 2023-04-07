package timmax.sokoban.controller;

import timmax.sokoban.model.SokobanModel;
import javafx.scene.input.KeyCode;

import static timmax.basetilemodel.Direction.DOWN;
import static timmax.basetilemodel.Direction.LEFT;
import static timmax.basetilemodel.Direction.RIGHT;
import static timmax.basetilemodel.Direction.UP;

public class SokobanController {
    private final SokobanModel baseSokobanModel;

    public SokobanController(SokobanModel baseSokobanModel) {
        this.baseSokobanModel = baseSokobanModel;
    }

    public void onKeyPress( KeyCode keyCode) {
/*
        switch ( keyCode) {
            case Q ->          { baseSokobanModel.moveUndo( );  }
            case P ->          { baseSokobanModel.moveRedo( );  }
            case LEFT ->       { baseSokobanModel.move( LEFT);  }
            case RIGHT ->      { baseSokobanModel.move( RIGHT); }
            case UP ->         { baseSokobanModel.move( UP);    }
            case DOWN ->       { baseSokobanModel.move( DOWN);  }
            case BACK_SPACE -> { baseSokobanModel.decLevel( );  }
            case SPACE ->      { baseSokobanModel.incLevel( );  }
            case ESCAPE ->     { baseSokobanModel.restart( );   }
        }
*/
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