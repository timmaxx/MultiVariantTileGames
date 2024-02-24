package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public class EventOfServer60ForgetGameMatch extends EventOfServer {
    @Override
    public void executeOnClient(IModelOfClient iModelOfClient) {
        System.out.println("  onForgetGameMatch");
        iModelOfClient.getLocalClientState().forgetServerBaseModel();
    }

    @Override
    public String toString() {
        return "EventOfServer60ForgetGameMatch{}";
    }
}
