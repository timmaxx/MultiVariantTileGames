package timmax.tilegame.basemodel.protocol;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerLoader;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient31GetGameTypeSet extends EventOfClient {
    @Override
    public <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        System.out.println("  onGetGameTypeSet");

        ModelOfServerLoader modelLoader;
        try {
            modelLoader = new ModelOfServerLoader(
                    Paths.get(Objects.requireNonNull(ModelOfServerLoader.class.getResource("models.txt")).toURI())
            );
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        Set<ModelOfServerDescriptor> collectionOfModelOfServerDescriptor = modelLoader.getCollectionOfModelOfServerDescriptor();
        transportOfServer.getRemoteClientStateByClientId(clientId).setGameTypeSet(collectionOfModelOfServerDescriptor);
    }

    @Override
    public String toString() {
        return "EventOfClient31GetGameTypeSet{}";
    }
}
