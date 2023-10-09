package timmax.tilegame.guiengine.jfx.controller;

import timmax.tilegame.basemodel.BaseModel;

// Содержит контролеры по принятию событий от мыши над GameStackPane
public abstract class GameStackPaneController {
    protected BaseModel baseModel;

    public GameStackPaneController( BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    public abstract void onMousePrimaryClick( int x, int y);

    public abstract void onMouseSecondaryClick( int x, int y);
}