package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

//  Событие сервера.
//  (базовый класс несущий информацию о том, что состояние клиента в целом изменено, кроме ...92...).
public abstract class EventOfServer extends Event {
    public abstract void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton);
}
