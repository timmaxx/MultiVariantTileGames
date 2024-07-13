package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient50ForgetGameMatchSet extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.forgetGameMatchXSet();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
