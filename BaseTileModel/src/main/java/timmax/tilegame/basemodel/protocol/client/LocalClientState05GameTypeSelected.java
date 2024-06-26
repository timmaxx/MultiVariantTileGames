package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState05GameTypeSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

import java.util.Set;

public class LocalClientState05GameTypeSelected extends ClientState05GameTypeSelected<InstanceIdOfModel> {
    public LocalClientState05GameTypeSelected(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
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
    public void setGameMatchSet(Set<InstanceIdOfModel> setOfServerBaseModel) {
        super.setGameMatchSet(setOfServerBaseModel);
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnGetGameMatchSet();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
