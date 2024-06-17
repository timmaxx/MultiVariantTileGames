package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState05GameTypeSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoteClientState05GameTypeSelected<ClientId> extends ClientState05GameTypeSelected<IModelOfServer, ClientId> {
    public RemoteClientState05GameTypeSelected(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(clientStateAutomaton, transportOfServer, clientId);
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public void forgetGameType() {
        super.forgetGameType();
        getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer40ForgetGameType());
    }

    // ---- 5 Перечень партий
    @Override
    public void setGameMatchSet(Set<IModelOfServer> setOfServerBaseModel) {
        super.setGameMatchSet(setOfServerBaseModel);
        getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer51GetGameMatchSet(
                setOfServerBaseModel
                        .stream()
                        .map(InstanceIdOfModel::modelOfServerToInstanceIdOfModel)
                        .collect(Collectors.toSet())
        ));
    }

    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        throw new RuntimeException("Not available for this class!");
    }
}
