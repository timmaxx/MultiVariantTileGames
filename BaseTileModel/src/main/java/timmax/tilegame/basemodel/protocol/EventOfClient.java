package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public abstract class EventOfClient<T> extends Event {

    public abstract void execute(TransportOfServer<T> transportOfServer, T clientId);

    @Override
    public String toString() {
        return "TransportPackageOfClient{}";
    }

    // sendLogoutAnswer используется только в классе TransportPackageOfClient011Login
    protected void sendLogoutAnswer(TransportOfServer<T> transportOfServer, T clientId) {
        transportOfServer.send(clientId, new EventOfServer010Logout<>());
    }

    // sendLoginAnswer используется только в классе TransportPackageOfClient010Logout и TransportPackageOfClient010Logout
    protected void sendLoginAnswer(TransportOfServer<T> transportOfServer, T clientId, String userName) {
        transportOfServer.send(clientId, new EventOfServer011Login<>(userName));
    }
}