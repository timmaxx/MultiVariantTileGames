package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient50ForgetGameMatchSet extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        System.out.println("  onForgetGameMatchSet");
        transportOfServer.getRemoteClientStateByClientId(clientId).forgetGameMatchSet();
    }

    @Override
    public String toString() {
        return "EventOfClient50ForgetGameMatchSet{}";
    }
}
