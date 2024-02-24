package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public class EventOfClient71StartGameMatchPlaying extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientState<ClientId> remoteClientState) {
        System.out.println("  onStartGameMatchPlaying");
        remoteClientState.setGameIsPlaying(true);
    }

    @Override
    public String toString() {
        return "EventOfClient71StartGameMatchPlaying{}";
    }
}
