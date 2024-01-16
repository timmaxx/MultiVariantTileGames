package timmax.tilegame.basemodel.protocol.server;

import java.util.Set;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.transport.TransportOfServer;

public class RemoteClientState<ClienId> extends AbstractClientState<IModelOfServer<ClienId>> {
    private final ClienId clientId;
    private final TransportOfServer<ClienId> transportOfServer;

    public RemoteClientState(TransportOfServer<ClienId> transportOfServer, ClienId clientId) {
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    // ---- 2 (Пользователь)
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

    // ---- 3 (Список типов игр)
    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer20ForgetGameTypeSet());
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer21GetGameTypeSet(setOfModelOfServerDescriptor));
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public void forgetGameType() {
        super.forgetGameType();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer30ForgetGameType());
    }

    @Override
    public void setGameType(IModelOfServer<ClienId> modelOfServer) {
        super.setGameType(modelOfServer);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer31GameTypeSelect(modelOfServer.getGameName()));
    }
}

/*
public class RemoteClientState<ClienId> extends AbstractClientState<IModelOfServer<ClienId>> {

    // ---- 4 (Конкретный тип игры)
    @Override
    public void forgetGameType() {
        super.forgetGameType();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer30ForgetGameType());
    }

    @Override
    public void setGameType(IModelOfServer<ClienId> modelOfServer) {
        super.setGameType(modelOfServer);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer31GameTypeSelect(modelOfServer.getGameName()));
    }
}
*/
