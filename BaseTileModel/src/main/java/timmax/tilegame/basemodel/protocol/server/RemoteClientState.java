package timmax.tilegame.basemodel.protocol.server;

import java.util.List;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.transport.TransportOfServer;

public class RemoteClientState<ClienId> extends AbstractClientState<IModelOfServer<ClienId>> {
    private final ClienId clientId;
    private final TransportOfServer<ClienId> transportOfServer;

    public RemoteClientState(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    // 1
    @Override
    public void forgetUserName() {
        super.forgetUserName();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer10Logout());
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer11Login(userName));
    }

    // 2
    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer20ForgetGameTypeSet());
    }

    @Override
    public void getGameTypeSet() { // ToDo: get или set (как 'setUserName(String userName)')???
        super.getGameTypeSet();
        // ToDo: Если set, то следующее значение передавать сюда параметром,
        //       вычисление которого как раз и вынести в вызывающую логику.
        List<String> listOfServerBaseModelString = transportOfServer
                .getCollectionOfModelOfServerDescriptor()
                .stream()
                .map(ModelOfServerDescriptor::getGameName)
                .toList();
        transportOfServer.sendEventOfServer( clientId, new EventOfServer21GetGameTypeSet(listOfServerBaseModelString));
    }

    // 3
    @Override
    public void forgetGameType() {
        super.forgetGameType();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer30ForgetGameType());
    }
}
