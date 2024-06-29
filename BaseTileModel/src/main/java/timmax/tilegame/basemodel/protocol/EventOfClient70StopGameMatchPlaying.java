package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient70StopGameMatchPlaying<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        logger.debug("  onStopGameMatchPlaying");
        remoteClientStateAutomaton.forgetGameIsPlaying();
    }

    @Override
    public String toString() {
        return "EventOfClient70StopGameMatchPlaying{}";
    }
}
