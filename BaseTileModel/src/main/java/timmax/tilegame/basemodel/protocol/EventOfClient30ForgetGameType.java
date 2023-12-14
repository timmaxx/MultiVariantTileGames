package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient30ForgetGameType<T> extends EventOfClient<T> {

    @Override
    public void execute(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onForgetGameTypeSet");
        transportOfServer.send(clientId, new EventOfServer30ForgetGameType<>());
    }

    @Override
    public String toString() {
        return "TransportPackageOfClient30ForgetGameType{}";
    }
}