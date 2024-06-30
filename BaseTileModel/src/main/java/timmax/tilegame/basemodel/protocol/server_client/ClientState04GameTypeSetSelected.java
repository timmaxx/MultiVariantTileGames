package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public abstract class ClientState04GameTypeSetSelected<Model> extends AbstractClientState<Model> implements IClientState04GameTypeSetSelected {
    public ClientState04GameTypeSetSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    protected void setGameTypeSet_(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        getClientStateAutomaton().clientState05GameTypeSelected.forgetGameType();
        getClientStateAutomaton().setSetOfModelOfServerDescriptor0(setOfModelOfServerDescriptor);
    }

    // interface IClientState04GameTypeSetSelected
    @Override
    public Set<ModelOfServerDescriptor> getGameTypeSet() {
        return getClientStateAutomaton().getSetOfModelOfServerDescriptor0();
    }

    @Override
    public void forgetGameTypeSet() {
        setGameTypeSet_(null);
    }

    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        getClientStateAutomaton().clientState05GameTypeSelected.setGameType_(modelOfServerDescriptor);
    }
}
