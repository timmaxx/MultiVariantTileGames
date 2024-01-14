package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public class EventOfClient10Logout extends EventOfClient {

    @Override
    public <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        System.out.println("  onLogout");

        sendLogoutAnswer(transportOfServer, clientId);
    }

    @Override
    public String toString() {
        return "EventOfClient10Logout{}";
    }
}