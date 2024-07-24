package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient42ReauthorizeUser extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.reauthorizeUser();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                '}';
    }
}
