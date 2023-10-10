package timmax.tilegame.guiengine.jfx.controller;

import javafx.scene.input.KeyCode;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basecontroller.BaseController;

// Содержит контролеры по принятию событий от клавиатуры над GameScene
public abstract class GameSceneController extends BaseController {
    public GameSceneController( BaseModel baseModel) {
        super( baseModel);
    }

    public abstract void onKeyPressed( KeyCode keyCode);
}