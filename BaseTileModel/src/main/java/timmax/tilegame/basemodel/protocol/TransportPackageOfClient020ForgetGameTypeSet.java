package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class TransportPackageOfClient020ForgetGameTypeSet<T> extends TransportPackageOfClient<T> {

    @Override
    public void execute(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onForgetGameTypeSet");
        transportOfServer.send(clientId, new TransportPackageOfServer020ForgetGameTypeSet<>());
    }

    @Override
    public String toString() {
        return "TransportPackageOfClient020ForgetGameTypeSet{}";
    }
}