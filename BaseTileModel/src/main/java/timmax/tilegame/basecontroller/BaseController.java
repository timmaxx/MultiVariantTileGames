package timmax.tilegame.basecontroller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.gamecommand.GameCommandKeyPressed;
import timmax.tilegame.basemodel.gamecommand.GameCommandMouseClick;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.EventOfClient92GameCommand;
import timmax.tilegame.transport.TransportOfClient;

public class BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    private final TransportOfClient transportOfClient;

    public BaseController(TransportOfClient transportOfClient) {
        this.transportOfClient = transportOfClient;
    }

    public void onKeyPressed(KeyCode keyCode) {
        logger.info("onKeyPressed()");
        logger.info("  keyCode = {}", keyCode);
        GameCommand gameCommand = new GameCommandKeyPressed(keyCode);
        EventOfClient eventOfClient = new EventOfClient92GameCommand(gameCommand);
        sendEventOfClient(eventOfClient);
    }

    public final void onMouseClick(MouseButton mouseButton, int x, int y) {
        logger.info("onMouseClick()");
        logger.info("  mouseButton = {}, x = {}, y = {}", mouseButton, x, y);
        GameCommand gameCommand = new GameCommandMouseClick(x, y, mouseButton);
        EventOfClient eventOfClient = new EventOfClient92GameCommand(gameCommand);
        sendEventOfClient(eventOfClient);
    }

    private void sendEventOfClient(EventOfClient eventOfClient) {
        transportOfClient.sendEventOfClient(eventOfClient);
    }

    @Override
    public String toString() {
        return "BaseController{" +
                "transportOfClient=" + transportOfClient +
                '}';
    }
}
