package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient11OpenConnectWithoutUserIdentify extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        remoteClientStateAutomaton.connect();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
