package timmax.tilegame.guiengine.jfx.controller;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.BaseModel;

// Содержит контролеры по принятию событий от мыши над GameStackPane
public abstract class GameStackPaneController extends BaseController {
    public GameStackPaneController( BaseModel baseModel) {
        super( baseModel);
    }

    public abstract void onMousePrimaryClick( int x, int y);

    public abstract void onMouseSecondaryClick( int x, int y);
}