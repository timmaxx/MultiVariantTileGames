package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient40ForgetUser extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.changeStateTo02ConnectNonIdent();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
