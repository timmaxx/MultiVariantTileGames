package timmax.tilegame.basecontroller;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.transport.TransportOfClient;

public abstract class BaseController {
    private final TransportOfClient transportOfClient;

    public BaseController(TransportOfClient transportOfClient) {
        this.transportOfClient = transportOfClient;
    }

    public void sendGameCommand(GameCommand gameCommand) {
        transportOfClient.sendCommand(gameCommand);
    }

    public void sendEventOfClient(EventOfClient eventOfClient) {
        transportOfClient.send(eventOfClient);
    }
}
