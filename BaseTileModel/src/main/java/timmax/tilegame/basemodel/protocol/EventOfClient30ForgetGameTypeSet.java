package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient30ForgetGameTypeSet extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.forgetGameTypeSet();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
