package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public abstract class EventOfClient extends Event {
    public abstract <ClienId> void executeOnServer(TransportOfServer<ClienId> transportOfServer, ClienId clientId);

    @Override
    public String toString() {
        return "EventOfClient{}";
    }
}
