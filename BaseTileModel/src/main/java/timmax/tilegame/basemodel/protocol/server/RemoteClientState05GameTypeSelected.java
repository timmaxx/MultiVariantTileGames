package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState05GameTypeSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.baseview.View;

import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.stream.Collectors;

public class RemoteClientState05GameTypeSelected<ClientId> extends ClientState05GameTypeSelected<IModelOfServer, ClientId> {
    public RemoteClientState05GameTypeSelected(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // Overriden methods of class AbstractClientState
    // ---- 4 (Конкретный тип игры)
    @Override
    public void forgetGameType() {
        super.forgetGameType();
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer40ForgetGameType()
        );
    }

    // ---- 5 Перечень партий
    @Override
    public void setGameMatchSet(Set<IModelOfServer> setOfServerBaseModel) {
        super.setGameMatchSet(setOfServerBaseModel);
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer51GetGameMatchSet(
                        setOfServerBaseModel
                                .stream()
                                .map(InstanceIdOfModel::modelOfServerToInstanceIdOfModel)
                                .collect(Collectors.toSet())
                )
        );
    }

    // interface IClientState00
    // ToDo: delete from interface IClientState00 and from this class
    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        throw new RuntimeException("Not available for this class!");
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}
