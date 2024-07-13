package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.GameTypeFabric;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient31SetGameTypeSet extends EventOfClient {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton) {
        remoteClientStateAutomaton.setGameTypeSet(GameTypeFabric.getGameTypeSet());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
