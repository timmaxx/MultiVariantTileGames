package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient11OpenConnectWithoutUserIdentify extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.openConnectWithoutUserIdentify();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
