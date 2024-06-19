package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;

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

    // Overriden methods of class AbstractClientState
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

    // interface IClientState00
    // ToDo: delete from interface IClientState00 and from this class
    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.GAME_MATCH_SET_SELECTED;
    }
}
