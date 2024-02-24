package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public class EventOfClient70StopGameMatchPlaying extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientState<ClientId> remoteClientState) {
        System.out.println("  onStopGameMatchPlaying");
        remoteClientState.forgetGameIsPlaying();
    }

    @Override
    public String toString() {
        return "EventOfClient70StopGameMatchPlaying{}";
    }
}
