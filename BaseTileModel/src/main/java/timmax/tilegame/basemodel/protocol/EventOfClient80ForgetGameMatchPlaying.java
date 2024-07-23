package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient80ForgetGameMatchPlaying extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.forgetGameMatchPlaying();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
