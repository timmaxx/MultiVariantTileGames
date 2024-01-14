package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.AbstractClientState;
import timmax.tilegame.transport.TransportOfServer;

public class RemoteClientState<ClienId> extends AbstractClientState<IModelOfServer<ClienId>> {
    private final ListOfRemoteView<ClienId> listOfRemoteView;

    public RemoteClientState(TransportOfServer<ClienId> transportOfServer) {
        listOfRemoteView = new ListOfRemoteView<>(transportOfServer);
    }
}
