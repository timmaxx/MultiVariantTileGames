package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public abstract class EventOfClient<ClientId> extends Event {
    public abstract void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientState);

    @Override
    public String toString() {
        return "EventOfClient{}";
    }
}
