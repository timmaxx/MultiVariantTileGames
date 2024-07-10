package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

// ToDo: Вероятно параметризация ClientId этому классу не должна быть нужна.
public abstract class EventOfClient<ClientId> extends Event {
    public abstract void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
