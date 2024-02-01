package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

public class EventOfServer50ForgetGamePlaySet extends EventOfServer {
    @Override
    public void executeOnClient(TransportOfClient transportOfClient) {
        System.out.println("  onForgetGamePlaySet");

        transportOfClient.getLocalClientState().forgetGameMatchSet();
    }

    @Override
    public String toString() {
        return "EventOfServer50ForgetGamePlaySet{}";
    }
}
