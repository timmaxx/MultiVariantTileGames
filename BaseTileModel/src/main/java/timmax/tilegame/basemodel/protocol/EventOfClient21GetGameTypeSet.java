package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient21GetGameTypeSet extends EventOfClient {
    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onGetGameTypeSet");

        transportOfServer.send(
                clientId,
                new EventOfServer21GetGameTypeSet(
                        transportOfServer.getCollectionOfModelOfServerDescriptor()
                                .stream()
                                // .map(x -> x.getGameName())
                                .map(ModelOfServerDescriptor::getGameName)
                                .toList()
                )
        );
    }

    @Override
    public String toString() {
        return "EventOfClient21GetGameTypeSet{}";
    }
}
