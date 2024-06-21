package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState05GameTypeSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

import java.util.Set;

public class LocalClientState05GameTypeSelected<Model> extends ClientState05GameTypeSelected<Model> {
    public LocalClientState05GameTypeSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 4 (Конкретный тип игры)
    @Override
    public void forgetGameType() {
        super.forgetGameType();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnForgetGameType();
    }

    // ---- 5 (Набор моделей игр)
    @Override
    public void setGameMatchSet(Set<Model> setOfServerBaseModel) {
        super.setGameMatchSet(setOfServerBaseModel);
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnGetGameMatchSet();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton<Model> getClientStateAutomaton() {
        return (LocalClientStateAutomaton<Model>)(super.getClientStateAutomaton());
    }
}
