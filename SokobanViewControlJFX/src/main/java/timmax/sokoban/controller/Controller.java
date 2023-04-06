package timmax.sokoban.controller;

import timmax.sokoban.model.Model;
import timmax.sokoban.model.gameobject.Direction;
import javafx.scene.input.KeyCode;


public class Controller {
    private final Model baseModel;

    public Controller( Model baseModel) {
        this.baseModel = baseModel;
    }

    public void onKeyPress( KeyCode keyCode) {
        /*
        switch ( keyCode) {
//            case Q:          baseModel.moveUndo( );             return;
            case P:          baseModel.moveRedo( );             return;
            case LEFT:       baseModel.move( Direction.LEFT);   return;
            case RIGHT:      baseModel.move( Direction.RIGHT);  return;
            case UP:         baseModel.move( Direction.UP);     return;
            case DOWN:       baseModel.move( Direction.DOWN);   return;
            case ESCAPE:     baseModel.restart( );              return;
            case BACK_SPACE: baseModel.startPrevLevel( );       return;
            case SPACE:      baseModel.startNextLevel( );       return;
        }
        */
        switch ( keyCode) {
//            case Q:          baseModel.moveUndo( );              return;
            case P ->          { baseModel.moveRedo( );            return; }
            case LEFT ->       { baseModel.move( Direction.LEFT);  return; }
            case RIGHT ->      { baseModel.move( Direction.RIGHT); return; }
            case UP ->         { baseModel.move( Direction.UP);    return; }
            case DOWN ->       { baseModel.move( Direction.DOWN);  return; }
            case ESCAPE ->     { baseModel.restart( );             return; }
            case BACK_SPACE -> { baseModel.startPrevLevel( );      return; }
            case SPACE ->      { baseModel.startNextLevel( );      return; }
        }
    }
}