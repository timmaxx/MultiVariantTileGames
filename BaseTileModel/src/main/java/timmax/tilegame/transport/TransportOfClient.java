package timmax.tilegame.transport;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.EventOfClient;

public interface TransportOfClient {
    void sendCommand(GameCommand gameCommand);

    void send(EventOfClient transportPackageOfClient);

    LocalClientState getLocalClientState();

    HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent();
}
