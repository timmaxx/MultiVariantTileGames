package timmax.tilegame.transport;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.protocol.ClientState;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.EventOfClient;

public interface TransportOfClient<T> {
    void sendCommand(GameCommand gameCommand);

    void send(EventOfClient<T> transportPackageOfClient);

    ClientState<Object> getClientState();

    HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent();
}