package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;

public interface LocalClientStateFabric {
    LocalClientState newLocalClientState(IModelOfClient iModelOfClient, BaseController baseController);
}