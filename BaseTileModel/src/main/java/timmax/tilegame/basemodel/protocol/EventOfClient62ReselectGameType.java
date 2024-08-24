package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient62ReselectGameType extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        remoteClientStateAutomaton.reselectGameType();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
