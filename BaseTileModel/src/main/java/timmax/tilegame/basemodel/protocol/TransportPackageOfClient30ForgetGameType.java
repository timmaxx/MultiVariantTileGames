package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfModel;

public class TransportPackageOfClient30ForgetGameType<T> extends TransportPackageOfClient<T> {

    @Override
    public void execute(TransportOfModel<T> transportOfModel, T clientId) {
        System.out.println("onForgetGameTypeSet");
        transportOfModel.send(clientId, new TransportPackageOfServer30ForgetGameType<>());
    }

    @Override
    public String toString() {
        return "TransportPackageOfClient30ForgetGameType{}";
    }
}