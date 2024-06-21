package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class LocalClientState04GameTypeSetSelected<Model> extends ClientState04GameTypeSetSelected<Model> {
    public LocalClientState04GameTypeSetSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 3 (Список типов игр)
    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnForgetGameTypeSet();
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public void setGameType(ModelOfServerDescriptor modelOfServerDescriptor) {
        super.setGameType(modelOfServerDescriptor);
        getClientStateAutomaton().getMapOfViewName_View().clear();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnSelectGameType();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton<Model> getClientStateAutomaton() {
        return (LocalClientStateAutomaton<Model>)(super.getClientStateAutomaton());
    }
}
