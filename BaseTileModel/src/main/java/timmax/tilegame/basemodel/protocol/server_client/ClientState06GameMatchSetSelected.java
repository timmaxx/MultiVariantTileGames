package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Set;

public abstract class ClientState06GameMatchSetSelected<Model> extends AbstractClientState<Model> implements IClientState06GameMatchSetSelected<Model> {
    public ClientState06GameMatchSetSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public Set<Model> getGameMatchSet() {
        return getClientStateAutomaton().getGameMatchXSet0();
    }

    @Override
    public void forgetGameMatchSet() {
        getClientStateAutomaton().setGameMatchSet_(null);
    }

    @Override
    public void setGameMatch(Model serverBaseModel) {
        getClientStateAutomaton().setServerBaseModel_(serverBaseModel);
    }
}
