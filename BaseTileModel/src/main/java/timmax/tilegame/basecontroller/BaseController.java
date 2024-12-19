package timmax.tilegame.basecontroller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.placement.primitives.XYCoordinate;
import timmax.tilegame.basemodel.protocol.EventOfClient92GameCommand;
import timmax.tilegame.transport.TransportOfClient;

public class BaseController {
    private final TransportOfClient transportOfClient;

    public BaseController(TransportOfClient transportOfClient) {
        this.transportOfClient = transportOfClient;
    }

    public void onKeyPressed(KeyCode keyCode) {
        GameCommand gameCommand = new GameCommandKeyPressed(keyCode);
        EventOfClient92GameCommand eventOfClient92GameCommand = new EventOfClient92GameCommand(gameCommand);
        transportOfClient.sendEventOfClient92GameCommand(eventOfClient92GameCommand);
    }

    public final void onMouseClick(MouseButton mouseButton, XYCoordinate xyCoordinate) {
        GameCommand gameCommand = new GameCommandMouseClick(xyCoordinate, mouseButton);
        EventOfClient92GameCommand eventOfClient92GameCommand = new EventOfClient92GameCommand(gameCommand);
        transportOfClient.sendEventOfClient92GameCommand(eventOfClient92GameCommand);
    }

    @Override
    public String toString() {
        return "BaseController{" +
                "transportOfClient=" + transportOfClient +
                '}';
    }
}
