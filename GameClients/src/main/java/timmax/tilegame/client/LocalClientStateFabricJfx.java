package timmax.tilegame.client;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.protocol.LocalClientStateFabric;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.basemodel.protocol.client.jfx.LocalClientStateJfx;

public class LocalClientStateFabricJfx implements LocalClientStateFabric {
    public LocalClientState newLocalClientState(IModelOfClient iModelOfClient, BaseController baseController) {
        return new LocalClientStateJfx(iModelOfClient, baseController);
    }
}
