package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState02ConnectNonIdent;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public abstract class LocalClientState02ConnectNonIdent<Model, ClientId> extends ClientState02ConnectNonIdent<Model, ClientId> {
    public LocalClientState02ConnectNonIdent(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // Overriden methods of class AbstractClientState
    // ---- 2 (Пользователь)
    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnLogin();
    }
}
