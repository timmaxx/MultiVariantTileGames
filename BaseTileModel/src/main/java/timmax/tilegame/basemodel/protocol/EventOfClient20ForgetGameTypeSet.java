package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient20ForgetGameTypeSet extends EventOfClient {

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onForgetGameTypeSet");
        transportOfServer.send(clientId, new EventOfServer20ForgetGameTypeSet());
    }

    @Override
    public String toString() {
        return "EventOfClient20ForgetGameTypeSet{}";
    }
}
