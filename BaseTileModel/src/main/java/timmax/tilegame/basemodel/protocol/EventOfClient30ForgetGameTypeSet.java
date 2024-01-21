package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient30ForgetGameTypeSet extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        System.out.println("  onForgetGameTypeSet");
        transportOfServer.getRemoteClientStateByClientId(clientId).forgetGameTypeSet();
    }

    @Override
    public String toString() {
        return "EventOfClient30ForgetGameTypeSet{}";
    }
}
