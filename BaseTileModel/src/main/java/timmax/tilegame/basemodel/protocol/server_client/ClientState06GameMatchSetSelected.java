package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState06GameMatchSetSelected<GameMatchX extends IGameMatchX> extends ClientState04GameTypeSetSelected<GameMatchX> {
    public ClientState06GameMatchSetSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public GameType getGameType() {
        return getClientStateAutomaton().getGameType_();
    }

    @Override
    public void resetGameType() {
        GameType gameType = getClientStateAutomaton().getGameType_();
        Set<GameMatchX> gameMatchXSet = getClientStateAutomaton().getGameMatchXSet_();

        setGameType(gameType, gameMatchXSet);
    }

    @Override
    public Set<GameMatchX> getGameMatchXSet() {
        return getClientStateAutomaton().getGameMatchXSet_();
    }

    @Override
    public void setGameMatchX(GameMatchX gameMatchX) {
        getClientStateAutomaton().setGameMatchX_(gameMatchX);
    }
}
