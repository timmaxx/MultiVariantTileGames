package timmax.tilegameenginejfx;

import javafx.scene.input.KeyCode;
import timmax.basetilemodel.BaseModel;

public abstract class GameController {
    protected final BaseModel baseModel;
    protected final Game game;


    public GameController( BaseModel baseModel, Game game) {
        this.baseModel = baseModel;
        this.game = game;
    }

    public void onMouseLeftClick( int x, int y) {
    }

    public void onMouseRightClick( int x, int y) {
    }

    public void onKeyPress( KeyCode keyCode) {
    }

    public void onKeyReleased( KeyCode keyCode) {
    }
}