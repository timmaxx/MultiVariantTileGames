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

    private IModelOfClient<Model> iModelOfClient;
    private MultiGameWebSocketClient<Model> transportOfClient;
    private URI uri;

    public MultiGameWebSocketClientManyTimesUse() {
        super();
        // ToDo: Здесь не инициализируется iModelOfClient, поэтому выводится константа в лог.
        //       Но возможно, что-бы не использовать null, стоит реализовать класс, реализующий IClientState01NoConect
        //       и здесь им инициализировать.
        logger.info("  Main game client status: {}.", "NO_CONNECT");
    }

    // interface TransportOfClient:
    @Override
    public void setModelOfClient(IModelOfClient iModelOfClient) {
        this.iModelOfClient = iModelOfClient;
    }
/*
    @Override
    public boolean isOpen() {
        return transportOfClient != null && transportOfClient.isOpen();
    }

    @Override
    public boolean isClosed() {
        return transportOfClient == null || transportOfClient.isClosed();
    }
*/
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
        transportOfClient = new MultiGameWebSocketClient<>(uri/*, iModelOfClient*/);
        // ToDo: См. коммент к setModelOfClient
        transportOfClient.setModelOfClient(iModelOfClient);
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
