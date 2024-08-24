package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public abstract class EventOfClient extends Event {
    public abstract <ClientId> void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
