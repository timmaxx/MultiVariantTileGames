package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState05GameTypeSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

import java.util.Set;
import java.util.stream.Collectors;

public class RemoteClientState05GameTypeSelected<ClientId> extends ClientState05GameTypeSelected<IModelOfServer> {
    private final ClientId clientId;

    public RemoteClientState05GameTypeSelected(ClientStateAutomaton<IModelOfServer> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // class ClientState05GameTypeSelected
    // ---- 4 (Конкретный тип игры)
    @Override
    public void forgetGameType() {
        super.forgetGameType();
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                clientId,
                new EventOfServer40ForgetGameType()
        );
    }

    // ---- 5 Перечень партий
    @Override
    public void setGameMatchSet(Set<IModelOfServer> setOfServerBaseModel) {
        super.setGameMatchSet(setOfServerBaseModel);
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                clientId,
                new EventOfServer51SetGameMatchSet(
                        setOfServerBaseModel
                                .stream()
                                // См. комменты в class InstanceIdOfModel
                                .map(InstanceIdOfModel::modelOfServerToInstanceIdOfModel)
                                // .map(ModelOfServer::modelOfServerToInstanceIdOfModel)
                                .collect(Collectors.toSet())
                )
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}
