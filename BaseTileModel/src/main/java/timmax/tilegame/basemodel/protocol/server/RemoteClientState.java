package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.AbstractClientState;
import timmax.tilegame.basemodel.protocol.EventOfServer10Logout;
import timmax.tilegame.basemodel.protocol.EventOfServer11Login;
import timmax.tilegame.transport.TransportOfServer;

public class RemoteClientState<ClienId> extends AbstractClientState<IModelOfServer<ClienId>> {
    private final ClienId clientId;
    private final TransportOfServer<ClienId> transportOfServer;
    //private final ListOfRemoteView<ClienId> listOfRemoteView;

    public RemoteClientState(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
        // listOfRemoteView = new ListOfRemoteView<>(transportOfServer);
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
        if (userName != null && !userName.isEmpty()) {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer11Login(userName));
            System.out.println("after transportOfServer.sendEventOfServer(clientId, new EventOfServer11Login(userName));");
        } else {
            transportOfServer.sendEventOfServer(clientId, new EventOfServer10Logout());
            System.out.println("after transportOfServer.sendEventOfServer(clientId, new EventOfServer10Logout());");
        }
    }
}
