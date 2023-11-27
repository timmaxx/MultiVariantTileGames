package timmax.tilegame.transport;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.server.RemoteView;

public interface TransportOfModel<T> {
    // Вариант с одним параметром сейчас используется только потому, что и сервер работает только с одним клиентом.
    // ToDo: перевести сервер на режим работы с более чем одним клиентом.
    //       Потом удалить вариант с одним параметром.
    // 2. М: передача по сети сообщений из очереди модели
    void sendGameEvent(GameEvent gameEvent);

    // 2. М: передача по какому-то транспорту сообщений от модели на выборки
    void sendGameEvent(RemoteView<T> remoteView, GameEvent gameEvent);
}