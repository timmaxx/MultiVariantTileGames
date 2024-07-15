package timmax.tilegame.basemodel.protocol.server_client;

import java.util.HashSet;
import java.util.Set;

public abstract class ClientState06GameMatchSetSelected<Model> extends AbstractClientState<Model> implements IClientState06GameMatchSetSelected<Model> {
    public ClientState06GameMatchSetSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public Set<Model> getGameMatchXSet() {
        return getClientStateAutomaton().getGameMatchXSet_();
    }

    @Override
    public void forgetGameMatchXSet() {
        getClientStateAutomaton().setGameMatchXSet_(new HashSet<>());
    }

    @Override
    public void setGameMatchX(Model gameMatchX) {
        getClientStateAutomaton().setGameMatchX_(gameMatchX);
    }
}
