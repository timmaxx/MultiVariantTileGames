package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState02ConnectNonIdent<Model> extends AbstractClientState<Model> implements IClientState02ConnectNonIdent {
    public ClientState02ConnectNonIdent(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void setUser(String userName, Set<GameType> gameTypeSet) {
        if (userName == null || userName.isEmpty()) {
            throw new NullPointerException("UserName is null. It must be not null for this method.");
        }
        getClientStateAutomaton().setUserName_(userName, gameTypeSet);
    }
}
