package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.AbstractClientState;
import timmax.tilegame.basemodel.protocol.EventOfServer10Logout;
import timmax.tilegame.basemodel.protocol.EventOfServer11Login;
import timmax.tilegame.basemodel.protocol.EventOfServer20ForgetGameTypeSet;
import timmax.tilegame.basemodel.protocol.EventOfServer21GetGameTypeSet;
import timmax.tilegame.transport.TransportOfServer;

import java.util.List;

public class RemoteClientState<ClienId> extends AbstractClientState<IModelOfServer<ClienId>> {
    private final ClienId clientId;
    private final TransportOfServer<ClienId> transportOfServer;

    public RemoteClientState(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer11Login(userName));
    }

    @Override
    public void forgetUserName() {
        super.forgetUserName();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer10Logout());
    }

    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer20ForgetGameTypeSet());
    }

    @Override
    public void getGameTypeSet() { // ToDo: get или set???
        super.getGameTypeSet();
        // ToDo: Если set, то следующее значение передавать сюда параметром.
        List<String> listOfServerBaseModelString = transportOfServer
                .getCollectionOfModelOfServerDescriptor()
                .stream()
                .map(ModelOfServerDescriptor::getGameName)
                .toList();
        transportOfServer.sendEventOfServer( clientId, new EventOfServer21GetGameTypeSet(listOfServerBaseModelString));
    }
}
