package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient20ForgetUser<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        remoteClientStateAutomaton.forgetUser();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
