package timmax.tilegame.transport;

import java.lang.reflect.InvocationTargetException;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.basemodel.protocol.server.RemoteView;

public interface TransportOfServer<T> {
    // Вариант с одним параметром сейчас используется только потому, что и сервер работает только с одним клиентом.
    // ToDo: перевести сервер на режим работы с более чем одним клиентом.
    //       Потом удалить вариант с одним параметром.
    // 2. М: передача по сети сообщений из очереди модели
    void sendGameEvent(GameEvent gameEvent);

    // 2. М: передача по какому-то транспорту сообщений от модели на выборки
    void sendGameEvent(RemoteView<T> remoteView, GameEvent gameEvent);

    void send(T clientId, EventOfServer transportPackageOfServer);

    ModelOfServer<T> getModelOfServer();

    // void setModelOfServer(ModelOfServer<T> modelOfServer);
    void setModelOfServer(String modelOfServer) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
