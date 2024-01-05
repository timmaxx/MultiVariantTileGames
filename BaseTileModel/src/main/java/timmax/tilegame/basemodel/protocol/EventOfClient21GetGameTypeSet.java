package timmax.tilegame.basemodel.protocol;

import java.util.stream.Stream;

import timmax.tilegame.transport.TransportOfServer;

import static java.util.stream.Collectors.toList;

public class EventOfClient21GetGameTypeSet extends EventOfClient {
    @Override
    public <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onGetGameTypeSet");

        transportOfServer.send(clientId, new EventOfServer021GetGameTypeSet(
                Stream.of(
                        // ToDo: Перечень классов вариантов игр следует делать не константами в коде. Варианты:
                        //       - файл параметров,
                        //       - классы, хранящиеся по определённому пути.
                        "MinesweeperModel.class",
                        "SokobanModel.class"
                ).collect(toList())
        ));
    }

    @Override
    public String toString() {
        return "EventOfClient21GetGameTypeSet{}";
    }
}
