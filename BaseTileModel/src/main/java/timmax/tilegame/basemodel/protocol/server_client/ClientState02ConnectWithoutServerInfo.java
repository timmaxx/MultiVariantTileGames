package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState02ConnectWithoutServerInfo<GameMatchX extends IGameMatchX> extends ClientState01NoConnect<GameMatchX> {
    public ClientState02ConnectWithoutServerInfo(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void setGameTypeSet(Set<GameType> gameTypeSet) {
        getBaseStateAutomaton().setGameTypeSet_(gameTypeSet);
    }

    @Override
    public void close() {
        getBaseStateAutomaton().close_();
    }
}
