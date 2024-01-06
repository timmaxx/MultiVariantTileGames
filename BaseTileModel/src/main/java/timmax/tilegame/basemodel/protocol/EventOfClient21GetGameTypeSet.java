package timmax.tilegame.basemodel.protocol;

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
                                // ToDo: x.getModelOfServerClass().toString() - это полное имя класса,
                                //       но нужно сделать map(x -> x.getName()),
                                //       но для этого name должно быть правильно заполнено.
                                //       Да ещё что-то нужно будет делать c name для разных языков...
                                .map(x -> x.getModelOfServerClass().toString())
                                .toList()
                )
        );
    }

    @Override
    public String toString() {
        return "EventOfClient21GetGameTypeSet{}";
    }
}
