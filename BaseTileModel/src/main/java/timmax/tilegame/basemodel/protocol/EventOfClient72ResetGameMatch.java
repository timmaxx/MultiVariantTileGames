package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient72ResetGameMatch extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.resetGameMatchX();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
