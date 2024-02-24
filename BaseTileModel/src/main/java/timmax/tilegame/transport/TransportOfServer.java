package timmax.tilegame.transport;

import timmax.tilegame.basemodel.protocol.EventOfServer;

public interface TransportOfServer<ClientId> {
    void sendEventOfServer(ClientId clientId, EventOfServer transportPackageOfServer);
}
