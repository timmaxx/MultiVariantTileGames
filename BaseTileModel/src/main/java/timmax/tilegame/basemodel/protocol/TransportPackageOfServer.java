package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfController;

public abstract class TransportPackageOfServer<T> extends TransportPackage {

    public abstract void execute(TransportOfController<T> transportOfModel);

    @Override
    public String toString() {
        return "TransportPackageOfServer{}";
    }
}