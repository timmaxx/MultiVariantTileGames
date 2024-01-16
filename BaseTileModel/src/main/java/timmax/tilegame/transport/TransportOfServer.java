package timmax.tilegame.transport;

import java.util.Set;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server.RemoteClientState;
import timmax.tilegame.basemodel.protocol.server.RemoteView;

public interface TransportOfServer<ClienId> {
    void sendGameEventToRemoteView(RemoteView<ClienId> remoteView, GameEvent gameEvent);

    void sendEventOfServer(ClienId clientId, EventOfServer transportPackageOfServer);

    RemoteClientState<ClienId> getRemoteClientStateByClientId(ClienId clientId);

    Set<ModelOfServerDescriptor> getCollectionOfModelOfServerDescriptor();
}
