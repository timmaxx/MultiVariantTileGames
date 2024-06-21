package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class LocalClientState06GameMatchSetSelected<Model> extends ClientState06GameMatchSetSelected<Model> {
    public LocalClientState06GameMatchSetSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 5 (Набор моделей игр)
    @Override
    public void forgetGameMatchSet() {
        super.forgetGameMatchSet();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnForgetGameMatchSet();
    }

    // ---- 6 (Конкретная модель игры)
    @Override
    public void setServerBaseModel(Model serverBaseModel) {
        super.setServerBaseModel(serverBaseModel);
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnSelectGameMatch();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton<Model> getClientStateAutomaton() {
        return (LocalClientStateAutomaton<Model>)(super.getClientStateAutomaton());
    }
}
