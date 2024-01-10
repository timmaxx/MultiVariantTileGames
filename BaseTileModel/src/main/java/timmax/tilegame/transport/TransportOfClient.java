package timmax.tilegame.transport;

import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;

public interface TransportOfClient {
    void sendEventOfClient(EventOfClient eventOfClient);

    LocalClientState getLocalClientState();
    HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent();
}
