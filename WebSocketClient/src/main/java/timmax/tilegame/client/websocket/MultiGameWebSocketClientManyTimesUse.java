package timmax.tilegame.client.websocket;

import java.net.URI;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.transport.TransportOfClient;

// WebSocket клиент многоразовый
public class MultiGameWebSocketClientManyTimesUse implements TransportOfClient {
    private IModelOfClient iModelOfClient;
    private MultiGameWebSocketClient transportOfClient;
    private URI uri;

    public MultiGameWebSocketClientManyTimesUse() {
        super();
        System.out.println("getMainGameClientStatus() = " + getMainGameClientStatus());
    }

    public void setModelOfClient(IModelOfClient iModelOfClient) {
        this.iModelOfClient = iModelOfClient;
    }

    public MainGameClientStatus getMainGameClientStatus() {
        if (iModelOfClient == null) {
            return MainGameClientStatus.NO_CONNECT;
        }
        return iModelOfClient.getMainGameClientStatus();
    }

    public void setURI(URI uri) {
        this.uri = uri;
        if (transportOfClient == null) {
            return;
        }
        transportOfClient.close();
    }

    // Overriden methods from interface TransportOfClient:
    @Override
    public void sendEventOfClient(EventOfClient eventOfClient) {
        transportOfClient.sendEventOfClient(eventOfClient);
    }

    @Override
    public boolean isOpen() {
        return transportOfClient != null && transportOfClient.isOpen();
    }

    @Override
    public boolean isClosed() {
        return transportOfClient == null || transportOfClient.isClosed();
    }

    // 1
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
        transportOfClient = new MultiGameWebSocketClient(uri);
        transportOfClient.setModelOfClient(iModelOfClient);
        transportOfClient.connect();
    }
}
