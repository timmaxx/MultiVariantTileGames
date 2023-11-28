package timmax.tilegame.guiengine.jfx.controller;

import javafx.scene.input.KeyCode;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.transport.TransportOfController;

// Содержит контролеры по принятию событий от клавиатуры над GameScene
public abstract class GameSceneController extends BaseController {
    public GameSceneController(TransportOfController transportOfController) {
        super(transportOfController);
    }

    public abstract void onKeyPressed(KeyCode keyCode);
}