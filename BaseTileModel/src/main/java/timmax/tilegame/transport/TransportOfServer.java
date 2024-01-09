package timmax.tilegame.transport;

import java.util.Collection;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.EventOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServer;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server.RemoteView;

public interface TransportOfServer<T> {
    void sendGameEventToRemoteView(RemoteView<T> remoteView, GameEvent gameEvent);

    void sendEventOfServerToClient(T clientId, EventOfServer transportPackageOfServer);

    ModelOfServer<T> getModelOfServer();

    void setModelOfServer(ModelOfServer<T> modelOfServer);

    Collection<ModelOfServerDescriptor> getCollectionOfModelOfServerDescriptor();
}
