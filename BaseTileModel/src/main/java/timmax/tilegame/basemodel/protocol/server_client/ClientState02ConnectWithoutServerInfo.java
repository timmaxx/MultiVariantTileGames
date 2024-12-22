package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState02ConnectWithoutServerInfo extends ClientState01NoConnect {
    public ClientState02ConnectWithoutServerInfo(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void setGameTypeSet(Set<GameType<IGameMatchX>> gameTypeSet) {
        getBaseStateAutomaton().setGameTypeSet_(gameTypeSet);
    }

    @Override
    public void close() {
        getBaseStateAutomaton().close_();
    }
}
