package timmax.tilegame.basecontroller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.EventOfClient92GameCommand;
import timmax.tilegame.transport.TransportOfClient;

public class BaseController {
    private final TransportOfClient transportOfClient;

    public BaseController(TransportOfClient transportOfClient) {
        this.transportOfClient = transportOfClient;
    }

    public void sendEventOfClient(EventOfClient eventOfClient) {
        transportOfClient.sendEventOfClient(eventOfClient);
    }

    public void onKeyPressed(KeyCode keyCode) {
        System.out.println("class BaseController. method onKeyPressed");
        System.out.println("  keyCode = " + keyCode);
        GameCommand gameCommand = new GameCommandKeyPressed(keyCode);
        EventOfClient eventOfClient = new EventOfClient92GameCommand(gameCommand);
        sendEventOfClient(eventOfClient);
    }

    public final void onMouseClick(MouseButton mouseButton, int x, int y) {
        System.out.println("class GameStackPaneController. method onMouseClick");
        System.out.println("  mouseButton = " + mouseButton + ", x = " + x + ", y = " + y);
        GameCommand gameCommand = new GameCommandMouseClick(x, y, mouseButton);
        EventOfClient eventOfClient = new EventOfClient92GameCommand(gameCommand);
        sendEventOfClient(eventOfClient);
    }

    @Override
    public String toString() {
        return "BaseController{" +
                "transportOfClient=" + transportOfClient +
                '}';
    }
}
