package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerLoader;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient31GiveGameTypeSet<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientState, ClientId clientId) {
        logger.debug("  onGetGameTypeSet");
        remoteClientState.setGameTypeSet(
                ModelOfServerLoader.getCollectionOfModelOfServerDescriptor(
                        remoteClientState, clientId
                )
        );
    }

    @Override
    public String toString() {
        return "EventOfClient31GiveGameTypeSet{}";
    }
}
