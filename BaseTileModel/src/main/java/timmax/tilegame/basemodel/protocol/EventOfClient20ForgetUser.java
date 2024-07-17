package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient20ForgetUser extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.forgetUser();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
