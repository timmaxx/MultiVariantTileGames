package timmax.tilegame.transport;

import java.util.Collection;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.server.IModelOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server.RemoteView;

public interface TransportOfServer<ClienId> {
    void sendGameEventToRemoteView(RemoteView<ClienId> remoteView, GameEvent gameEvent);

    void sendEventOfServer(ClienId clientId, EventOfServer transportPackageOfServer);

    IModelOfServer<ClienId> getModelByClientId(ClienId clientId);

    void addClienId_IModelOfServer(ClienId clientId, IModelOfServer<ClienId> iModelOfServer);

    Collection<ModelOfServerDescriptor> getCollectionOfModelOfServerDescriptor();
}
