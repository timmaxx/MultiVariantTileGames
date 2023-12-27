package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.AbstractClientState;
import timmax.tilegame.transport.TransportOfServer;

public class RemoteClientState<T> extends AbstractClientState {
    private final ListOfRemoteView<T> listOfRemoteView;

    public RemoteClientState(TransportOfServer<T> transportOfServer) {
        listOfRemoteView = new ListOfRemoteView<>(transportOfServer);
    }
/*
    public void addViewName(T clientId, String viewName) {
        listOfRemoteView.add(new RemoteView<>(clientId, viewName));
    }

    public ListOfRemoteView<T> getListOfRemoteView() {
        return listOfRemoteView;
    }
*/
}
