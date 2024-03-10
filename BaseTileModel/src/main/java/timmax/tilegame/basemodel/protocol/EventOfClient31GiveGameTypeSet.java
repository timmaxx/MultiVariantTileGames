package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerLoader;
import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public class EventOfClient31GiveGameTypeSet extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientState<ClientId> remoteClientState) {
        logger.debug("  onGetGameTypeSet");
        remoteClientState.setGameTypeSet(ModelOfServerLoader.getCollectionOfModelOfServerDescriptor(
                remoteClientState
        ));
    }

    @Override
    public String toString() {
        return "EventOfClient31GiveGameTypeSet{}";
    }
}
