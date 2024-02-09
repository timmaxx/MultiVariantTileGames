package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient70StopGameMatchPlaying extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        System.out.println("  onStopGameMatchPlaying");
        transportOfServer.getRemoteClientStateByClientId(clientId).forgetGameIsPlaying();
    }

    @Override
    public String toString() {
        return "EventOfClient70StopGameMatchPlaying{}";
    }
}
