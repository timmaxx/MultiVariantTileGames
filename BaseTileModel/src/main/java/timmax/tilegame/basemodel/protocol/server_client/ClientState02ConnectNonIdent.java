package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState02ConnectNonIdent extends ClientState01NoConnect {
    public ClientState02ConnectNonIdent(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void authorizeUser(String userName, Set<GameType> gameTypeSet) {
        getClientStateAutomaton().authorizeUser_(userName, gameTypeSet);
    }

    @Override
    public void close() {
        getClientStateAutomaton().close_();
    }
}
