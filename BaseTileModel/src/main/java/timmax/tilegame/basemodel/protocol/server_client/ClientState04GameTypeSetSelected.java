package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;

import java.util.Set;

public abstract class ClientState04GameTypeSetSelected<Model, ClientId> extends AbstractClientState<Model, ClientId> implements IClientState04GameTypeSetSelected {
    protected Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor; // ---- 3 (Список типов игр)

    public ClientState04GameTypeSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // ToDo: сделать его private после реализации всех подобных следующих комментариев.
    protected void setGameTypeSet_(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        // ToDo: вместо вызова с null параметром, следует вызывать соответствующий forgetXxx();
        getClientStateAutomaton().clientState05GameTypeSelected.setGameType_(null);
        this.setOfModelOfServerDescriptor = setOfModelOfServerDescriptor;
    }

    // interface IClientState04GameTypeSetSelected
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
}
