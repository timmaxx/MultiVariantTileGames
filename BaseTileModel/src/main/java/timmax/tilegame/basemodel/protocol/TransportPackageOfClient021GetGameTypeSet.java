package timmax.tilegame.basemodel.protocol;

import java.util.stream.Stream;

import timmax.tilegame.transport.TransportOfModel;

import static java.util.stream.Collectors.toList;

public class TransportPackageOfClient021GetGameTypeSet<T> extends TransportPackageOfClient<T> {

    @Override
    public void execute(TransportOfModel<T> transportOfModel, T clientId) {
        System.out.println("onGetGameTypeSet");

        transportOfModel.send(clientId, new TransportPackageOfServer021GetGameTypeSet<>(
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
        return "TransportPackageOfClient021GetGameTypeSet{}";
    }
}