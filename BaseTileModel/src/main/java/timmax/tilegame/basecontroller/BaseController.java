package timmax.tilegame.basecontroller;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.transport.TransportOfController;

public abstract class BaseController {
    protected BaseModel baseModel;
    protected TransportOfController transportOfController;


    public BaseController(BaseModel baseModel, TransportOfController transportOfController) {
        this.baseModel = baseModel;
        this.transportOfController = transportOfController;
    }

    public void sendCommand(GameCommand gameCommand) {
        transportOfController.sendCommand(gameCommand);
    }
}