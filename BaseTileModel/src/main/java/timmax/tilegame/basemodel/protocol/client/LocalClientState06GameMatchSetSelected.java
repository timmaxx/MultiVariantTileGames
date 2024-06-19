package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public abstract class LocalClientState06GameMatchSetSelected<Model, ClientID> extends ClientState06GameMatchSetSelected<Model, ClientID> {
    public LocalClientState06GameMatchSetSelected(ClientStateAutomaton<Model, ClientID> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // Overriden methods of class AbstractClientState
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
}
