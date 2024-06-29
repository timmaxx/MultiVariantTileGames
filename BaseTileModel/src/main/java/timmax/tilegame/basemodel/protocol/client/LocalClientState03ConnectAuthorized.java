package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.basemodel.protocol.server_client.ClientState03ConnectAuthorized;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

import java.util.Set;

public class LocalClientState03ConnectAuthorized extends ClientState03ConnectAuthorized<InstanceIdOfModel> {
    public LocalClientState03ConnectAuthorized(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 2
    @Override
    public void forgetUser() {
        super.forgetUser();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnLogout();
    }

    // ---- 3
    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnGetGameTypeSet();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
