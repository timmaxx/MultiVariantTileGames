package timmax.tilegameenginejfx;

import javafx.scene.input.KeyCode;

public interface GameScreenController {
    void onMouseLeftClick( int x, int y);

    void onMouseRightClick( int x, int y);

    void onKeyPress( KeyCode keyCode);

    void onKeyReleased( KeyCode keyCode);
}