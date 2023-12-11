package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfModel;

public class TransportPackageOfClient010Logout<T> extends TransportPackageOfClient {

    public TransportPackageOfClient010Logout() {
        super();
    }

    @Override
    public void execute(TransportOfModel transportOfModel, Object clientId) {
        System.out.println("onLogout");

        sendLogoutAnswer(transportOfModel, clientId);
    }

    @Override
    public String toString() {
        return "TransportPackageOfClient010Logout{}";
    }
}