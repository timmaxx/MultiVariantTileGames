package timmax.tilegame.transport;

import timmax.tilegame.basemodel.protocol.EventOfClient;
import timmax.tilegame.basemodel.protocol.TypeOfEvent;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;

public interface TransportOfClient {
    void sendEventOfClient(EventOfClient eventOfClient);

    LocalClientState getLocalClientState();
    void updateConnectStatePane(TypeOfEvent getGameTypeSet);
}
