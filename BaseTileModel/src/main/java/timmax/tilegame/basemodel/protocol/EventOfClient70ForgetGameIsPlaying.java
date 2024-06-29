package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient70ForgetGameIsPlaying<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        logger.debug("  onForgetGameIsPlaying");
        remoteClientStateAutomaton.forgetGameIsPlaying();
    }

    @Override
    public String toString() {
        return "EventOfClient70ForgetGameIsPlaying{}";
    }
}
