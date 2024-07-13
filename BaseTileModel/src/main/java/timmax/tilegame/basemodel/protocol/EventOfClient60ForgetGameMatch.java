package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient60ForgetGameMatch extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.forgetGameMatchX();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
