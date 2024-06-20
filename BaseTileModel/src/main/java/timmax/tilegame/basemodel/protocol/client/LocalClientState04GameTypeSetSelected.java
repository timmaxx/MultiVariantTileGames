package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class LocalClientState04GameTypeSetSelected<Model, ClientId> extends ClientState04GameTypeSetSelected<Model, ClientId> {
    public LocalClientState04GameTypeSetSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // Overriden methods of class AbstractClientState
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
    public LocalClientStateAutomaton<Model, ClientId> getClientStateAutomaton() {
        return (LocalClientStateAutomaton<Model, ClientId>)(super.getClientStateAutomaton());
    }
}
