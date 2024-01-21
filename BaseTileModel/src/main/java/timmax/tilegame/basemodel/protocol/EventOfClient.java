package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.transport.TransportOfServer;

public abstract class EventOfClient extends Event {
    public abstract <ClientId> void executeOnServer(TransportOfServer<ClientId> transportOfServer, ClientId clientId);

    @Override
    public String toString() {
        return "EventOfClient{}";
    }
}
