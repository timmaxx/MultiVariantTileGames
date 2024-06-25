package timmax.tilegame.client.websocket;

import java.net.URI;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.transport.TransportOfClient;

// WebSocket клиент многоразовый
public class MultiGameWebSocketClientManyTimesUse<Model> implements TransportOfClient<WebSocket> {
    private static final Logger logger = LoggerFactory.getLogger(MultiGameWebSocketClientManyTimesUse.class);

    // ToDo: О наличии переменной IModelOfClient в классах:
    //       В классе MultiGameWebSocketServer его "аналога" (т.е. IModelOfServer) нет.
    //       Не обнаружил места, где объявляется переменая типа IModelOfServer.
    //       Странно, но тогда, по единообразию и переменных IModelOfClient не должно быть.
    private IModelOfClient<Model> iModelOfClient;

    private MultiGameWebSocketClient<Model> transportOfClient;
    private URI uri;

    public MultiGameWebSocketClientManyTimesUse() {
        super();
        // ToDo: Возможно, что-бы не использовать null, стоит реализовать класс, реализующий IClientState01NoConnect
        //       и здесь им инициализировать.
        logger.info("  Main game client status: {}.", "NO_CONNECT");
    }

    // interface TransportOfClient:
    @Override
    public void setModelOfClient(IModelOfClient iModelOfClient) {
        this.iModelOfClient = iModelOfClient;
    }

    @Override
    public void close() {
        if (transportOfClient == null) {
            return;
        }
        transportOfClient.close();
        transportOfClient = null;
    }

    @Override
    public void connect() {
        transportOfClient = new MultiGameWebSocketClient<>(uri, this);
        // ToDo: См. коммент к setModelOfClient
        transportOfClient.connect();
    }

    @Override
    public void setURI(URI uri) {
        this.uri = uri;
        if (transportOfClient == null) {
            return;
        }
        transportOfClient.close();
    }

    @Override
    public void sendEventOfClient(EventOfClient<WebSocket> eventOfClient) {
        transportOfClient.sendEventOfClient(eventOfClient);
    }
}
