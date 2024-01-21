package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer60ForgetGameMatch extends EventOfServer {
    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onForgetGameMatch");
        transportOfClient.getLocalClientState().forgetServerBaseModel();
    }

    @Override
    public String toString() {
        return "EventOfServer60ForgetGameMatch{}";
    }
}
