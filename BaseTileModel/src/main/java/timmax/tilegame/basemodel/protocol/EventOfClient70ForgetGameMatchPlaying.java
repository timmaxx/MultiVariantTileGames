package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient70ForgetGameMatchPlaying extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        remoteClientStateAutomaton.forgetGameMatchPlaying();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
