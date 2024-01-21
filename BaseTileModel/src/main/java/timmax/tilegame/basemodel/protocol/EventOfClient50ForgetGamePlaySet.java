package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient50ForgetGamePlaySet extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        System.out.println("  onForgetGamePlaySet");
        transportOfServer.getRemoteClientStateByClientId(clientId).forgetGamePlaySet();
    }

    @Override
    public String toString() {
        return "EventOfClient50ForgetGamePlaySet{}";
    }
}
