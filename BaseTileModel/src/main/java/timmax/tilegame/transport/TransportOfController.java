package timmax.tilegame.transport;

import timmax.tilegame.basemodel.gamecommand.GameCommand;
import timmax.tilegame.basemodel.protocol.ClientState;
import timmax.tilegame.basemodel.protocol.HashSetOfObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.TransportPackageOfClient;

public interface TransportOfController<T> {
    void sendCommand(GameCommand gameCommand);

    void send(TransportPackageOfClient<T> transportPackageOfClient);

    ClientState<Object> getClientState();

    HashSetOfObserverOnAbstractEvent getHashSetOfObserverOnAbstractEvent();
}