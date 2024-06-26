package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerLoader;
import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient31SetGameTypeSet<ClientId> extends EventOfClient<ClientId> {
    // ToDo: Вероятно нужно переработать код executeOnServer(...) для двух классов (см. ниже) и вероятно перестать
    //       использовать в них clientId.
    //       Комментарий относится:
    //       - в первую очередь к классам:
    //       -- EventOfClient31SetGameTypeSet,
    //       -- EventOfClient61SetGameMatch,
    //       - во вторых:
    //       -- к EventOfClient,
    //       - в третьих:
    //       -- ко всем остальным EventOfClientХХ...
    //       clientId используется в executeOnServer(...) в классах-наследниках EventOfClient только в:
    //       - EventOfClient31SetGameTypeSet
    //       -- используется с рефлексией,
    //       - EventOfClient61SetGameMatch (используется с рефлексией)
    //       -- используется с рефлексией,
    //       -- явно вызывается sendEventOfServer, в которую передаётся clientId.
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        remoteClientStateAutomaton.setGameTypeSet(
                ModelOfServerLoader.getCollectionOfGameType(
                        remoteClientStateAutomaton, clientId
                )
        );
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
