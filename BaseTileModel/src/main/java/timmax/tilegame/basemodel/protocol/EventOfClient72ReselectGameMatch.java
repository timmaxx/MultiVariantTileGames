package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient72ReselectGameMatch extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.reselectGameMatch();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
