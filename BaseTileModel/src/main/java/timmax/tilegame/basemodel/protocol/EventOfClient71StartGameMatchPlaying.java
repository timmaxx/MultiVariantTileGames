package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient71StartGameMatchPlaying extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        System.out.println("  onStartGameMatchPlaying");
        transportOfServer.getRemoteClientStateByClientId(clientId).setGameIsPlaying(true);
    }

    @Override
    public String toString() {
        return "EventOfClient71StartGameMatchPlaying{}";
    }
}
