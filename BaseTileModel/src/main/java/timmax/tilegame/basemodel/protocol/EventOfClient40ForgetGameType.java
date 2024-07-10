package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient40ForgetGameType<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        remoteClientStateAutomaton.forgetGameType();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
