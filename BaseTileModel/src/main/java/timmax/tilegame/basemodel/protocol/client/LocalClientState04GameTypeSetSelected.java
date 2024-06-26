package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

public class LocalClientState04GameTypeSetSelected extends ClientState04GameTypeSetSelected<InstanceIdOfModel> {
    public LocalClientState04GameTypeSetSelected(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
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
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
