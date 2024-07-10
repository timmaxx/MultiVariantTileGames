package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

// ToDo: Вероятно параметризация ClientId этому классу не должна быть нужна.
public abstract class EventOfClient<ClientId> extends Event {
    // ToDo: Вероятно нужно переработать код executeOnServer(...) для классов (см. ниже) и вероятно перестать
    //       использовать в них clientId.
    //       Комментарий относится:
    //       - в первую очередь к классу:
    //       -- к EventOfClient,
    //       - во вторых:
    //       -- ко всем остальным EventOfClientХХ...
    public abstract void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
