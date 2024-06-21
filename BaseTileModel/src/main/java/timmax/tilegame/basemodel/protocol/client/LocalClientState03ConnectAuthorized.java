package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.ClientState03ConnectAuthorized;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

import java.util.Set;

public class LocalClientState03ConnectAuthorized<Model> extends ClientState03ConnectAuthorized<Model> {
    public LocalClientState03ConnectAuthorized(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 2 (Пользователь)
    @Override
    public void forgetUserName() {
        super.forgetUserName();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnLogout();
    }

    // ---- 3 (Список типов игр)
    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnGetGameTypeSet();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton<Model> getClientStateAutomaton() {
        return (LocalClientStateAutomaton<Model>)(super.getClientStateAutomaton());
    }
}
