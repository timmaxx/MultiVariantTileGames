package timmax.tilegame.guiengine.jfx.controller;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.transport.TransportOfController;

// Содержит контролеры по принятию событий от мыши над GameStackPane
public abstract class GameStackPaneController extends BaseController {
    public GameStackPaneController(TransportOfController transportOfController) {
        super(transportOfController);
    }

    public abstract void onMousePrimaryClick(int x, int y);

    public abstract void onMouseSecondaryClick(int x, int y);
}