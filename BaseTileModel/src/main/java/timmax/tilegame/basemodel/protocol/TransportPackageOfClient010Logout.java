package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class TransportPackageOfClient010Logout<T> extends TransportPackageOfClient<T> {

    @Override
    public void execute(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onLogout");

        sendLogoutAnswer(transportOfServer, clientId);
    }

    @Override
    public String toString() {
        return "TransportPackageOfClient010Logout{}";
    }
}