package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Set;

public abstract class ClientState06GameMatchSetSelected<Model> extends AbstractClientState<Model> implements IClientState06GameMatchSetSelected<Model> {
    public ClientState06GameMatchSetSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    protected void setGameMatchSet_(Set<Model> setOfServerBaseModel) {
        getClientStateAutomaton().clientState07GameMatchSelected.forgetGameMatch();
        getClientStateAutomaton().setSetOfServerBaseModel0(setOfServerBaseModel);
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public Set<Model> getGameMatchSet() {
        return getClientStateAutomaton().getSetOfServerBaseModel0();
    }

    @Override
    public void forgetGameMatchSet() {
        setGameMatchSet_(null);
    }

    @Override
    public void setGameMatch(Model serverBaseModel) {
        getClientStateAutomaton().clientState07GameMatchSelected.setServerBaseModel_(serverBaseModel);
    }
}
