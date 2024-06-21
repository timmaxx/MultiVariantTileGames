package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public abstract class ClientState05GameTypeSelected<Model> extends AbstractClientState<Model> implements IClientState05GameTypeSelected<Model> {
    protected ModelOfServerDescriptor modelOfServerDescriptor; // ---- 4 (Конкретный тип игры)

    public ClientState05GameTypeSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    protected void setGameType_(ModelOfServerDescriptor modelOfServerDescriptor) {
        getClientStateAutomaton().clientState06GameMatchSetSelected.forgetGameMatchSet();
        this.modelOfServerDescriptor = modelOfServerDescriptor;
    }

    // interface IClientState05GameTypeSelected
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
}
