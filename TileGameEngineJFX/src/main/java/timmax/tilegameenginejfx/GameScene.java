package timmax.tilegameenginejfx;

import javafx.scene.Parent;
import javafx.scene.Scene;

// Это:
// - либо как-бы мега ViewJfx, на которой будут размещены несколько ViewJfx.
// - либо в такой сцене должно быть по одной ViewJfx/
public class GameScene extends Scene {
    // private final GameController gameController;


    public GameScene( Parent root) {
        super( root);
/*
        setOnKeyPressed( );
        setOnKeyReleased( );
*/
    }

/*
    // setOnKeyReleased в классе Scene объявлен final, поэтому не получилось его перегрузить в этом классе.
    private void setOnKeyReleased( ) {
        setOnKeyReleased( event -> gameController.onKeyReleased( event.getCode( )));
    }

    // setOnKeyPressed в классе Scene объявлен final, поэтому не получилось его перегрузить в этом классе.
    private void setOnKeyPressed( ) {
        setOnKeyPressed( event -> gameController.onKeyPress( event.getCode( )));
    }
*/
}