package timmax.tilegame.transport;

import java.net.URI;

import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

// ToDo: Непонятно, почему класс параметризирован ClientId? Ведь он используется на клиенте. В частности
//       sendEventOfClient(...)
//       отправляет событие от клиента серверу и событие не содержит идентификатора клиента.
//       Но сервер, приняв событие от клиента, знает идентификатор клиента и как-то по нему будет у себя понимать,
//       сообщение пришло от того или иного клиента.
// ToDo: класс не должен здесь параметризироватья Model.
public interface TransportOfClient<ClientId> {
    void setModelOfClient(IModelOfClient iModelOfClient);
/*
    boolean isOpen();
    boolean isClosed();
*/
    void close();
    void connect();

    //  setURI(URI uriFromControls) Нужен в обоих классах, но в
    //  class MultiGameWebSocketClient
    //  т.к. он
    //  extends org.java_websocket.client.WebSocketClient
    //  это не получается. Также смотри кооментарии к MultiGameWebSocketClient.
    void setURI(URI uriFromControls);

    void sendEventOfClient(EventOfClient<ClientId> eventOfClient);
}
