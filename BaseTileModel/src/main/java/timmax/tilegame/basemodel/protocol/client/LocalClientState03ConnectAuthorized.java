package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.ClientState03ConnectAuthorized;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

import java.util.Set;

public abstract class LocalClientState03ConnectAuthorized<Model, ClientId> extends ClientState03ConnectAuthorized<Model, ClientId> {
    public LocalClientState03ConnectAuthorized(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

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
}
