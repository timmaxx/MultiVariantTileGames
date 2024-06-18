package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public abstract class ClientState05GameTypeSelected<Model, ClientId> extends AbstractClientState2<Model, ClientId> implements IClientState05GameTypeSelected<Model> {
    protected ModelOfServerDescriptor modelOfServerDescriptor; // ---- 4 (Конкретный тип игры)

    public ClientState05GameTypeSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    protected void setGameType_(ModelOfServerDescriptor modelOfServerDescriptor) {
        getClientStateAutomaton().clientState06GameMatchSetSelected.setGameMatchSet_(null);
        this.modelOfServerDescriptor = modelOfServerDescriptor;
    }

    @Override
    public ModelOfServerDescriptor getGameType() {
        return modelOfServerDescriptor;
    }

    @Override
    public void forgetGameType() {
        setGameType_(null);
    }

    @Override
    public void setGameMatchSet(Set<Model> setOfServerBaseModel) {
        getClientStateAutomaton().clientState06GameMatchSetSelected.setGameMatchSet_(setOfServerBaseModel);
    }

    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_TYPE_SELECTED;
    }
}
