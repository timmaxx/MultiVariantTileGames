package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.transport.TransportOfServer;

import java.util.Set;

public abstract class ClientState04GameTypeSetSelected<Model, ClientId> extends AbstractClientState2<Model, ClientId> implements IClientState04GameTypeSetSelected {
    protected Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor; // ---- 3 (Список типов игр)

    public ClientState04GameTypeSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    public ClientState04GameTypeSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(clientStateAutomaton, transportOfServer, clientId);
    }

    protected void setGameTypeSet_(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        getClientStateAutomaton().clientState05GameTypeSelected.setGameType_(null);
        this.setOfModelOfServerDescriptor = setOfModelOfServerDescriptor;
    }

    @Override
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        return setOfModelOfServerDescriptor;
    }

    @Override
    public void forgetGameTypeSet() {
        setGameTypeSet_(null);
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        getClientStateAutomaton().clientState05GameTypeSelected.setGameType_(modelOfServerDescriptor);
    }

    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_TYPE_SET_SELECTED;
    }
}
