package timmax.sokoban.controller;

import timmax.sokoban.model.SokobanModel;
import javafx.scene.input.KeyCode;

import static timmax.sokoban.model.gameobject.Direction.*;

public class Controller {
    private final SokobanModel baseSokobanModel;

    public Controller( SokobanModel baseSokobanModel) {
        this.baseSokobanModel = baseSokobanModel;
    }

    public void onKeyPress( KeyCode keyCode) {
        switch ( keyCode) {
//          case Q ->          { baseSokobanModel.moveUndo( );  return; }
//          case P ->          { baseSokobanModel.moveRedo( );  return; }
            case LEFT ->       { baseSokobanModel.move( LEFT);  return; }
            case RIGHT ->      { baseSokobanModel.move( RIGHT); return; }
            case UP ->         { baseSokobanModel.move( UP);    return; }
            case DOWN ->       { baseSokobanModel.move( DOWN);  return; }
            case SPACE ->      { baseSokobanModel.incLevel( );  return; }
            case BACK_SPACE -> { baseSokobanModel.decLevel( );  return; }
            case ESCAPE ->     { baseSokobanModel.restart( );   return; }
        }
    }
}