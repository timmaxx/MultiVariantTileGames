package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfModel;

public class TransportPackageOfClient010Logout<T> extends TransportPackageOfClient<T> {

    @Override
    public void execute(TransportOfModel<T> transportOfModel, T clientId) {
        System.out.println("onLogout");

        sendLogoutAnswer(transportOfModel, clientId);
    }

    @Override
    public String toString() {
        return "TransportPackageOfClient010Logout{}";
    }
}