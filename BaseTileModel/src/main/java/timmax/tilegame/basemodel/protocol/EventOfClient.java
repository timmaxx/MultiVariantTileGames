package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

//  Событие клиента
//  (базовый класс несущий информацию о желании клиента изменить состояние клиента в целом, кроме ...92...).
public abstract class EventOfClient extends Event {
    public abstract void executeOnServer(RemoteClientStateAutomaton remoteClientStateAutomaton);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
