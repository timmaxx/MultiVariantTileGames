package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState02ConnectNonIdent;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

public class LocalClientState02ConnectNonIdent extends ClientState02ConnectNonIdent<InstanceIdOfModel> {
    public LocalClientState02ConnectNonIdent(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 2 (Пользователь)
    @Override
    public void setUser(String userName) {
        super.setUser(userName);
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnLogin();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
