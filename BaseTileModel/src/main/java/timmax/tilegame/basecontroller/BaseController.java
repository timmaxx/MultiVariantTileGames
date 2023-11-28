package timmax.tilegame.basecontroller;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.transport.TransportOfController;

public abstract class BaseController {
    protected TransportOfController transportOfController;


    public BaseController(TransportOfController transportOfController) {
        this.transportOfController = transportOfController;
    }

    public void sendCommand(GameCommand gameCommand) {
        transportOfController.sendCommand(gameCommand);
    }
}