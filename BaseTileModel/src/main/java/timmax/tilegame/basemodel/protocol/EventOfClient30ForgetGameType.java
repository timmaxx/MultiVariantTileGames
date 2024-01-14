package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient30ForgetGameType extends EventOfClient {
    @Override
    public <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        System.out.println("  onForgetGameTypeSet");
        transportOfServer.getRemoteClientStateByClientId(clientId).forgetGameType();
    }

    @Override
    public String toString() {
        return "EventOfClient30ForgetGameType{}";
    }
}
