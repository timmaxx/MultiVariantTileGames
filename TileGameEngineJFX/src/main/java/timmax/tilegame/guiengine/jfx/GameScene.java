package timmax.tilegame.guiengine.jfx;

import javafx.scene.Parent;
import javafx.scene.Scene;

import timmax.tilegame.guiengine.jfx.controller.GameSceneController;

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