package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient42ReauthorizeUser extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        remoteClientStateAutomaton.reauthorizeUser();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                '}';
    }
}
