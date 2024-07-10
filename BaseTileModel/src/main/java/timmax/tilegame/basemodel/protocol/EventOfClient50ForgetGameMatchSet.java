package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient50ForgetGameMatchSet<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        remoteClientStateAutomaton.forgetGameMatchXSet();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
