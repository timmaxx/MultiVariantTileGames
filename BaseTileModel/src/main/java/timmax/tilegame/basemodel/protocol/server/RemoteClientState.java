package timmax.tilegame.basemodel.protocol.server;

import java.util.List;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
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
        transportOfServer.sendEventOfServer(clientId, new EventOfServer20Logout());
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer21Login(userName));
    }

    // ---- 3 (Список типов игр)
    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer30ForgetGameTypeSet());
    }

    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer31GetGameTypeSet(setOfModelOfServerDescriptor));
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public void forgetGameType() {
        super.forgetGameType();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer40ForgetGameType());
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        super.setGameType(modelOfServerDescriptor);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer41GameTypeSelect(modelOfServerDescriptor));
    }

    // ---- 5 Перечень партий
    public void forgetGamePlaySet() {
        super.setGamePlaySet(null);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer50ForgetGamePlaySet());
    }

    public void setGamePlaySet(List<IModelOfServer<ClienId>> listOfServerBaseModel) {
        super.setGamePlaySet(listOfServerBaseModel);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer51GetGamePlaySet(
                listOfServerBaseModel
                        .stream()
                        .map(InstanceIdOfModel::modelOfServerToInstanceIdOfModel)
                        .toList()
        ));
    }

    // ---- 6 Конкретная партия
/*
    @Override
    public void forgetServerBaseModel() {
        super.forgetServerBaseModel();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer60(
        ));
    }
*/
    @Override
    // ToDo: в идентификаторе ClienId есть ошибка. Заменить здесь и во всех классах на ClientId.
    public void setServerBaseModel(IModelOfServer<ClienId> iModelOfServer) {
        super.setServerBaseModel(serverBaseModel);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer61GameMatchSelect(
                new InstanceIdOfModel(iModelOfServer.toString())
        ));
    }
}
