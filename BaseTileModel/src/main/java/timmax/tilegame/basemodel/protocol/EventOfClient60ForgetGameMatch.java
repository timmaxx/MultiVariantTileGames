package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient60ForgetGameMatch extends EventOfClient {
    @Override
    public <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        System.out.println("  onForgetGameMatch");
        transportOfServer.getRemoteClientStateByClientId(clientId).forgetServerBaseModel();
    }

    @Override
    public String toString() {
        return "EventOfClient60ForgetGameMatch{}";
    }
}
