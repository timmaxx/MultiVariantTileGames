package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient30ForgetGameType extends EventOfClient {

    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onForgetGameTypeSet");
        transportOfServer.send(clientId, new EventOfServer30ForgetGameType());
    }

    @Override
    public String toString() {
        return "EventOfClient30ForgetGameType{}";
    }
}
