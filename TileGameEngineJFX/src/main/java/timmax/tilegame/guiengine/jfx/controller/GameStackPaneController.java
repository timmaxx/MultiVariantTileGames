package timmax.tilegame.guiengine.jfx.controller;

import javafx.scene.input.MouseButton;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.EventOfClient92GameCommand;
import timmax.tilegame.transport.TransportOfClient;

// Содержит контролер по принятию событий от мыши над GameStackPane
public class GameStackPaneController extends BaseController {
    public GameStackPaneController(TransportOfClient transportOfClient) {
        super(transportOfClient);
    }

    public final void onMouseClick(MouseButton mouseButton, int x, int y) {
        System.out.println("class GameStackPaneController. method onMouseClick");
        System.out.println("  mouseButton = " + mouseButton + ", x = " + x + ", y = " + y);
        GameCommand gameCommand = new GameCommandMouseClick(x, y, mouseButton);
        EventOfClient eventOfClient = new EventOfClient92GameCommand(gameCommand);
        sendEventOfClient(eventOfClient);
    }
}
