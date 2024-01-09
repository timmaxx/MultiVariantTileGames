package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public abstract class EventOfClient extends Event {
    public abstract <T> void executeOnServer(TransportOfServer<T> transportOfServer, T clientId);

    @Override
    public String toString() {
        return "EventOfClient{}";
    }

    // ToDo: sendLogoutAnswer используется только в классе EventOfClient11Login. Что-то сделать с этим.
    protected <T> void sendLogoutAnswer(TransportOfServer<T> transportOfServer, T clientId) {
        transportOfServer.sendEventOfServerToClient(clientId, new EventOfServer10Logout());
    }

    // ToDo: sendLoginAnswer используется только в классе EventOfClient10Logout и EventOfClient10Logout. Что-то сделать с этим.
    protected <T> void sendLoginAnswer(TransportOfServer<T> transportOfServer, T clientId, String userName) {
        transportOfServer.sendEventOfServerToClient(clientId, new EventOfServer11Login(userName));
    }
}
