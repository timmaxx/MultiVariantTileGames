package timmax.tilegame.basemodel.protocol;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerLoader;
import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public class EventOfClient31GiveGameTypeSet extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientState<ClientId> remoteClientState) {
        logger.debug("  onGetGameTypeSet");
        try {
            Set<ModelOfServerDescriptor> collectionOfModelOfServerDescriptor = ModelOfServerLoader.getCollectionOfModelOfServerDescriptor(
                    Paths.get(Objects.requireNonNull(ModelOfServerLoader.class.getResource("models.txt")).toURI()),
                    remoteClientState
            );
            remoteClientState.setGameTypeSet(collectionOfModelOfServerDescriptor);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "EventOfClient31GiveGameTypeSet{}";
    }
}
