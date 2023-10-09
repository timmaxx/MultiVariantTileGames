package timmax.tilegame.guiengine.jfx.controller;

import javafx.scene.input.KeyCode;

import timmax.tilegame.basemodel.BaseModel;

// Содержит контролеры по принятию событий от клавиатуры над GameScene
public abstract class GameSceneController {
    protected BaseModel baseModel;

    public GameSceneController( BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    public abstract void onKeyPressed( KeyCode keyCode);
}