package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient20Logout extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        System.out.println("  onLogout");
        transportOfServer.getRemoteClientStateByClientId(clientId).forgetUserName();
    }

    @Override
    public String toString() {
        return "EventOfClient20Logout{}";
    }
}
