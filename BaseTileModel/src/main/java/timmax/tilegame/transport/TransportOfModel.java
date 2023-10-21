package timmax.tilegame.transport;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public interface TransportOfModel {
    // 2. М: передача по сети сообщений из очереди модели
    void sendGameEvent( GameEvent gameEvent);
}