package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState06GameTypeWasSet<GameMatchX extends IGameMatchX> extends ClientState04UserWasAuthorized<GameMatchX> {
    public ClientState06GameTypeWasSet(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public void resetGameType() {
        GameType gameType = getClientStateAutomaton().getGameType_();
        setGameType(gameType);
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
