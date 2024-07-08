package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.GameTypeFabric;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient31SetGameTypeSet<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        remoteClientStateAutomaton.setGameTypeSet(GameTypeFabric.getGameTypeSet());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
