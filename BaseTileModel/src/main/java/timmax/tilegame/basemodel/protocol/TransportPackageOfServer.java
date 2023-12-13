package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfClient;

public abstract class TransportPackageOfServer<T> extends TransportPackage {

    public abstract void execute(TransportOfClient<T> transportOfClient);

    @Override
    public String toString() {
        return "TransportPackageOfServer{}";
    }
}