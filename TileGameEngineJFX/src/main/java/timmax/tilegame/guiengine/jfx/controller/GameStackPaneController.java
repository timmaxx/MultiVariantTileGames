package timmax.tilegame.guiengine.jfx.controller;

import javafx.scene.input.MouseButton;
import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.transport.TransportOfClient;

// Содержит контролеры по принятию событий от мыши над GameStackPane
public abstract class GameStackPaneController extends BaseController {
    public GameStackPaneController(TransportOfClient transportOfClient) {
        super(transportOfClient);
    }

    public abstract void onMouseClick(MouseButton mouseButton, int x, int y);
}
