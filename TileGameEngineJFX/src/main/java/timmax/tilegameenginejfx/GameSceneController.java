package timmax.tilegameenginejfx;

import javafx.scene.input.KeyCode;
import timmax.basetilemodel.BaseModel;

// Содержит контролеры по принятию событий от клавиатуры над GameScene
public abstract class GameSceneController {
    protected BaseModel baseModel;

    public GameSceneController( BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    protected abstract void onKeyPressed( KeyCode keyCode);
}