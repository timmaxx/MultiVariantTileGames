package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.HashSet;
import java.util.Set;

public abstract class ClientState04GameTypeSetSelected<GameMatchX extends IGameMatchX> extends ClientState02ConnectNonIdent<GameMatchX> {
    public ClientState04GameTypeSetSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState04GameTypeSetSelected
    @Override
    public Set<GameType> getGameTypeSet() {
        return getClientStateAutomaton().getGameTypeSet_();
    }

    @Override
    public void forgetUser() {
        getClientStateAutomaton().setGameTypeSet_(new HashSet<>());
    }

    @Override
    public void setGameType(GameType gameType, Set<GameMatchX> gameMatchXSet) {
        getClientStateAutomaton().setGameType_(gameType, gameMatchXSet);
    }
}
