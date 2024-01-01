package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public abstract class EventOfClient extends Event {
    public abstract <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId);

    @Override
    public String toString() {
        return "EventOfClient{}";
    }

    // sendLogoutAnswer используется только в классе EventOfClient011Login
    protected <T> void sendLogoutAnswer(TransportOfServer<T> transportOfServer, T clientId) {
        transportOfServer.send(clientId, new EventOfServer010Logout());
    }

    // sendLoginAnswer используется только в классе EventOfClient010Logout и EventOfClient010Logout
    protected <T> void sendLoginAnswer(TransportOfServer<T> transportOfServer, T clientId, String userName) {
        transportOfServer.send(clientId, new EventOfServer011Login(userName));
    }
}
