package timmax.tilegame.basemodel.protocol.server;

import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Set;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.AbstractClientState;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

public class RemoteClientState<ClientId> extends AbstractClientState<IModelOfServer> {
    private final ClientId clientId;
    private final TransportOfServer<ClientId> transportOfServer;

    private Set<String> setOfViewName;

    public RemoteClientState(TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        this.clientId = clientId;
        this.transportOfServer = transportOfServer;
    }

    public Set<String> getSetOfViewName() {
        return setOfViewName;
    }

    public TransportOfServer<ClientId> getTransportOfServer() {
        return transportOfServer;
    }

    public ClientId getClientId() {
        return clientId;
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
        if(modelOfServerDescriptor == null) {
            setOfViewName = null;
            transportOfServer.sendEventOfServer(clientId, new EventOfServer40ForgetGameType());
            return;
        }
        setOfViewName = new HashSet<>();
        // ToDo: Сейчас foreach работает и с ключём и со значением (аналогично как в классе LocalClientState),
        //       Но здесь достаточно только с ключём.
        for (Map.Entry<String, Class<? extends View>> entry : modelOfServerDescriptor.getMapOfViewNameViewClass().entrySet()) {
            setOfViewName.add(entry.getKey());
        }
        transportOfServer.sendEventOfServer(clientId, new EventOfServer41SetGameType(modelOfServerDescriptor.getGameName()));
    }

    // ---- 5 Перечень партий
    @Override
    public void forgetGameMatchSet() {
        super.setGameMatchSet(null);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer50ForgetGameMatchSet());
    }

    @Override
    public void setGameMatchSet(Set<IModelOfServer> setOfServerBaseModel) {
        super.setGameMatchSet(setOfServerBaseModel);
        transportOfServer.sendEventOfServer(clientId, new EventOfServer51GetGameMatchSet(
                setOfServerBaseModel
                        .stream()
                        .map(InstanceIdOfModel::modelOfServerToInstanceIdOfModel)
                        .collect(Collectors.toSet())
        ));
    }

    // ---- 6 Конкретная партия игры
    @Override
    public void forgetServerBaseModel() {
        super.forgetServerBaseModel();
        transportOfServer.sendEventOfServer(clientId, new EventOfServer60ForgetGameMatch());
    }

    @Override
    public void setServerBaseModel(IModelOfServer iModelOfServer) {
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
        // ToDo: Вызов этого метода может быть как для модели:
        //       - для которой ранее ещё не было вызвано createNewGame()
        //       - так и для той, у которой был вызов createNewGame(), но потом она была поставлена на паузу.
        serverBaseModel.createNewGame();
    }
}
