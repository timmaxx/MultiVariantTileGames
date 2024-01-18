package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient50ForgetGamePlaySet extends EventOfClient {
    @Override
    public <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        System.out.println("  onForgetGamePlaySet");
        transportOfServer.getRemoteClientStateByClientId(clientId).forgetGamePlaySet();
    }

    @Override
    public String toString() {
        return "EventOfClient50ForgetGamePlaySet{}";
    }
}
