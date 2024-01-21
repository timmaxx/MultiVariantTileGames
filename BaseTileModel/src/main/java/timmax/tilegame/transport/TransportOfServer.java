package timmax.tilegame.transport;

import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public interface TransportOfServer<ClientId> {
    void sendEventOfServer(ClientId clientId, EventOfServer transportPackageOfServer);

    RemoteClientState<ClientId> getRemoteClientStateByClientId(ClientId clientId);
}
