package timmax.tilegame.transport;

import java.net.URI;

import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

// ToDo: класс не должен здесь параметризироватья Model
public interface TransportOfClient<Model, ClientId> {
    void setModelOfClient(IModelOfClient<Model, ClientId> iModelOfClient);

    boolean isOpen();
    boolean isClosed();

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
