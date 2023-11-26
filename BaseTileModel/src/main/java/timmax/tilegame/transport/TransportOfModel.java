package timmax.tilegame.transport;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public interface TransportOfModel {
    // 2. М: передача по сети сообщений из очереди модели
    void sendGameEvent(GameEvent gameEvent);
}

/*
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.server.RemoteView;

import java.util.Set;

public interface TransportOfModel {
    // 2. М: передача по какому-то транспорту сообщений от модели на выборки
    void sendGameEvent(Set<RemoteView> setOfRemoteViews, GameEvent gameEvent);
}
*/