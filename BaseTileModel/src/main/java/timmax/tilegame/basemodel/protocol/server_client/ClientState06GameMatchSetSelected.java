package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.transport.TransportOfServer;

import java.util.Set;

public abstract class ClientState06GameMatchSetSelected<Model, ClientId> extends AbstractClientState2<Model, ClientId> implements IClientState06GameMatchSetSelected<Model> {
    protected Set<Model> setOfServerBaseModel; // ---- 5 (Набор моделей игр)

    public ClientState06GameMatchSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    public ClientState06GameMatchSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(clientStateAutomaton, transportOfServer, clientId);
    }

    protected void setGameMatchSet_(Set<Model> setOfServerBaseModel) {
        getClientStateAutomaton().clientState07GameMatchSelected.setServerBaseModel_(null);
        this.setOfServerBaseModel = setOfServerBaseModel;
    }

    @Override
    public Set<Model> getGameMatchSet() {
        return setOfServerBaseModel;
    }

    @Override
    public void forgetGameMatchSet() {
        setGameMatchSet_(null);
    }

    @Override
    public void setServerBaseModel(Model serverBaseModel) {
        getClientStateAutomaton().clientState07GameMatchSelected.setServerBaseModel_(serverBaseModel);
    }

    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_MATCH_SET_SELECTED;
    }
}
