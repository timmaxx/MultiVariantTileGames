package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.AbstractClientState;
import timmax.tilegame.transport.TransportOfServer;

public class RemoteClientState<T> extends AbstractClientState<IModelOfServer<T>> {
    private final ListOfRemoteView<T> listOfRemoteView;

    public RemoteClientState(TransportOfServer<T> transportOfServer) {
        listOfRemoteView = new ListOfRemoteView<>(transportOfServer);
    }
}
