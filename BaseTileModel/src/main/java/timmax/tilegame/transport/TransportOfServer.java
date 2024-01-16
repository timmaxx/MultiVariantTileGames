package timmax.tilegame.transport;

import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public interface TransportOfServer<ClienId> {
    void sendEventOfServer(ClienId clientId, EventOfServer transportPackageOfServer);

    RemoteClientState<ClienId> getRemoteClientStateByClientId(ClienId clientId);
}
