package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient21GetGameTypeSet extends EventOfClient {
    @Override
    public <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        System.out.println("  onGetGameTypeSet");
        transportOfServer.getRemoteClientStateByClientId(clientId).getGameTypeSet();
    }

    @Override
    public String toString() {
        return "EventOfClient21GetGameTypeSet{}";
    }
}
