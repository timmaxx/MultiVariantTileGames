package timmax.tilegame.basecontroller;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.transport.TransportOfClient;

public abstract class BaseController {
    protected TransportOfClient transportOfClient;


    public BaseController(TransportOfClient transportOfClient) {
        this.transportOfClient = transportOfClient;
    }

    public void sendCommand(GameCommand gameCommand) {
        transportOfClient.sendCommand(gameCommand);
    }
}