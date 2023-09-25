package timmax.tilegameenginejfx;

import javafx.scene.Parent;
import javafx.scene.Scene;

// Это как-бы мега ViewJfx, на которой будут размещены несколько ViewJfx.
public class GameScene extends Scene {
    public GameScene( Parent root, GameSceneController gameSceneController) {
        super( root);

        if ( gameSceneController == null) {
            return;
        }
        setOnKeyPressed( event -> gameSceneController.onKeyPressed( event.getCode( )));
    }
}