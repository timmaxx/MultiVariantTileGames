package timmax.tilegame.basecontroller;

import timmax.tilegame.basemodel.BaseModel;
// import timmax.tilegame.basemodel.GameCommandQueue;

public abstract class BaseController {
    protected BaseModel baseModel;
    // protected GameCommandQueue gameCommandQueue;


    public BaseController( BaseModel baseModel) {
        this.baseModel = baseModel;
        // gameCommandQueue = new GameCommandQueue( );
    }
}