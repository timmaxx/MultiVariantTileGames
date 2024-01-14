package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient20ForgetGameTypeSet extends EventOfClient {
    @Override
    public <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        System.out.println("  onForgetGameTypeSet");
        transportOfServer.getRemoteClientStateByClientId(clientId).forgetGameTypeSet();
    }

    @Override
    public String toString() {
        return "EventOfClient20ForgetGameTypeSet{}";
    }
}
