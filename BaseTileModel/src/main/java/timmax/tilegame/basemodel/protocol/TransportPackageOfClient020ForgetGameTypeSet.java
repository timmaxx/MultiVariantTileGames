package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfModel;

public class TransportPackageOfClient020ForgetGameTypeSet<T> extends TransportPackageOfClient<T> {

    @Override
    public void execute(TransportOfModel transportOfModel, Object clientId) {
        System.out.println("onForgetGameTypeSet");
        transportOfModel.send(clientId, new TransportPackageOfServer020ForgetGameTypeSet());
    }

    @Override
    public String toString() {
        return "TransportPackageOfClient020ForgetGameTypeSet{}";
    }
}