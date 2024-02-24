package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public abstract class EventOfClient extends Event {
    public abstract <ClientId> void executeOnServer(RemoteClientState<ClientId> remoteClientState);

    @Override
    public String toString() {
        return "EventOfClient{}";
    }
}
