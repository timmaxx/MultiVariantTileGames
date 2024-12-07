package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient72ResetGameMatch extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        remoteClientStateAutomaton.resetGameMatch();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
