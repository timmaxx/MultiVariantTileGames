package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Set;

public abstract class ClientState06GameMatchSetSelected<Model, ClientId> extends AbstractClientState<Model, ClientId> implements IClientState06GameMatchSetSelected<Model> {
    protected Set<Model> setOfServerBaseModel; // ---- 5 (Набор моделей игр)

    public ClientState06GameMatchSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // ToDo: сделать его private после реализации всех подобных следующих комментариев.
    protected void setGameMatchSet_(Set<Model> setOfServerBaseModel) {
        // ToDo: вместо вызова с null параметром, следует вызывать соответствующий forgetXxx();
        getClientStateAutomaton().clientState07GameMatchSelected.setServerBaseModel_(null);
        this.setOfServerBaseModel = setOfServerBaseModel;
    }

    // interface IClientState06GameMatchSetSelected
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
}
