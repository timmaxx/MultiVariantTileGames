package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState02ConnectNonIdent<GameMatchX extends IGameMatchX> extends ClientState01NoConnect<GameMatchX> {
    public ClientState02ConnectNonIdent(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
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
