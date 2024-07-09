package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

// ToDo: Вероятно параметризация ClientId этому классу не должна быть нужна.
public abstract class EventOfClient<ClientId> extends Event {
    // ToDo: Вероятно нужно переработать код executeOnServer(...) для классов (см. ниже) и вероятно перестать
    //       использовать в них clientId.
    //       Комментарий относится:
    //       - в первую очередь к классу:
    //       -- EventOfClient61SetGameMatch,
    //       - во вторых:
    //       -- к EventOfClient,
    //       - в третьих:
    //       -- ко всем остальным EventOfClientХХ...
    //       clientId используется в executeOnServer(...) в классах-наследниках EventOfClient только в:
    //       - EventOfClient61SetGameMatch (используется с рефлексией)
    //       -- используется с рефлексией.
    public abstract void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
