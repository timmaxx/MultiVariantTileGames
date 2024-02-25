package timmax.tilegame.transport;

import java.net.URI;

import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;

public interface TransportOfClient {
    void sendEventOfClient(EventOfClient eventOfClient);

    boolean isOpen();
    boolean isClosed();

    void close();
    void connect();

    // ToDo: setURI(URI uriFromControls) Нужен в одном из классов, но не в обоих.
    void setURI(URI uriFromControls);

    void setModelOfClient(IModelOfClient iModelOfClient);
}
