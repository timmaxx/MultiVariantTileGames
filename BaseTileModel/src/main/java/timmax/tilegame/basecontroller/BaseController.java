package timmax.tilegame.basecontroller;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.transport.GameCommandQueueOfController;

public abstract class BaseController {
    protected BaseModel baseModel;
    protected GameCommandQueueOfController gameCommandQueueOfController;


    public BaseController( BaseModel baseModel) {
        this.baseModel = baseModel;
        gameCommandQueueOfController = new GameCommandQueueOfController( baseModel);
    }
}