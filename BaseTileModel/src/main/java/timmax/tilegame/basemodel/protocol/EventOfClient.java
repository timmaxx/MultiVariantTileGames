package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public abstract class EventOfClient extends Event {
    public abstract void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
