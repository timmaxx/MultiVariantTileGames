package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.GameTypeFabric;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient31SetGameTypeSet extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        remoteClientStateAutomaton.setGameTypeSet(GameTypeFabric.getGameTypeSet());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
