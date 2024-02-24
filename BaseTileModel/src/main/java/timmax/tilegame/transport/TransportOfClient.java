package timmax.tilegame.transport;

import timmax.tilegame.basemodel.protocol.EventOfClient;

public interface TransportOfClient {
    void sendEventOfClient(EventOfClient eventOfClient);
}
