package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Set;

public abstract class ClientState06GameTypeWasSet<GameMatchX extends IGameMatchX> extends ClientState04UserWasAuthorized<GameMatchX> {
    public ClientState06GameTypeWasSet(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public Set<GameMatchX> getGameMatchXSet() {
        return getBaseStateAutomaton().getGameMatchXSet_();
    }

    @Override
    public void resetGameType() {
        getBaseStateAutomaton().setGameType_(getBaseStateAutomaton().getGameType_());
    }

    @Override
    public void setGameMatchX(GameMatchX gameMatchX) {
        getBaseStateAutomaton().setGameMatchX_(gameMatchX);
    }
}
