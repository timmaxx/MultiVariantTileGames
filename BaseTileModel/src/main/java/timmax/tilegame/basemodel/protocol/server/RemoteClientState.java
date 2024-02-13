package timmax.tilegame.basemodel.protocol.server;

import java.util.List;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.baseview.ViewMainField;
import timmax.tilegame.transport.TransportOfServer;

public class RemoteClientState<ClientId> extends AbstractClientState<IModelOfServer<ClientId>> {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    public RemoteClientState(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
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
        transportOfServer.sendEventOfServer(clientId, new EventOfServer41SetGameType(modelOfServerDescriptor));
    }

    // ---- 5 Перечень партий
    public void forgetGameMatchSet() {
        super.setGameMatchSet(null);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer50ForgetGameMatchSet());
    }

    public void setGameMatchSet(List<IModelOfServer<ClientId>> listOfServerBaseModel) {
        super.setGameMatchSet(listOfServerBaseModel);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer51GetGameMatchSet(
                listOfServerBaseModel
                        .stream()
                        .map(InstanceIdOfModel::modelOfServerToInstanceIdOfModel)
                        .toList()
        ));
    }

    // ---- 6 Конкретная партия игры
    @Override
    public void forgetServerBaseModel() {
        super.forgetServerBaseModel();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer60ForgetGameMatch());
    }

    @Override
    public void setServerBaseModel(IModelOfServer<ClientId> iModelOfServer) {
        super.setServerBaseModel(iModelOfServer);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer61SetGameMatch(
                new InstanceIdOfModel(iModelOfServer.toString())
        ));
    }

    // ---- 7
    @Override
    public void forgetGameIsPlaying() {
        super.forgetGameIsPlaying();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer70GameMatchIsNotPlaying());
    }

    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        super.setGameIsPlaying(gameIsPlaying);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer71GameMatchIsPlaying());

        transportOfServer.sendEventOfServer(clientId, new EventOfServer91AddView(ViewMainField.class, "MainField"));
        serverBaseModel.createNewGame();
    }
}
