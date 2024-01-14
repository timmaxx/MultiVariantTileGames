package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public abstract class EventOfClient extends Event {
    public abstract <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId);

    @Override
    public String toString() {
        return "EventOfClient{}";
    }

    // ToDo: sendLogoutAnswer используется только в классе EventOfClient11Login. Что-то сделать с этим.
    protected <ClienId> void sendLogoutAnswer(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        transportOfServer.sendEventOfServer(clientId, new EventOfServer10Logout());
    }

    // ToDo: sendLoginAnswer используется только в классе EventOfClient10Logout и EventOfClient10Logout. Что-то сделать с этим.
    protected <ClienId> void sendLoginAnswer(TransportOfServer<ClienId> transportOfServer, ClienId clientId, String userName) {
        transportOfServer.sendEventOfServer(clientId, new EventOfServer11Login(userName));
    }
}
