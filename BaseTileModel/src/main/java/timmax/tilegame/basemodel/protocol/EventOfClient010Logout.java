package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient010Logout<T> extends EventOfClient<T> {

    @Override
    public void executeOnServer(TransportOfServer<T> transportOfServer, T clientId) {
        System.out.println("  onLogout");

        sendLogoutAnswer(transportOfServer, clientId);
    }

    @Override
    public String toString() {
        return "EventOfClient010Logout{}";
    }
}