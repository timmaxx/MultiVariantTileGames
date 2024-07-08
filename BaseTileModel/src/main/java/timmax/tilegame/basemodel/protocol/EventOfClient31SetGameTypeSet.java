package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.GameTypeFabric;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient31SetGameTypeSet<ClientId> extends EventOfClient<ClientId> {
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
    //       -- используется с рефлексией,
    //       -- явно вызывается sendEventOfServer, в которую передаётся clientId.
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        remoteClientStateAutomaton.setGameTypeSet(GameTypeFabric.getGameTypeSet());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
