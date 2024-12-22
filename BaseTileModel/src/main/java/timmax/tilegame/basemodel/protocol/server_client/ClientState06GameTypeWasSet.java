package timmax.tilegame.basemodel.protocol.server_client;

import java.util.Set;

public abstract class ClientState06GameTypeWasSet extends ClientState04UserWasAuthorized {
    public ClientState06GameTypeWasSet(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public Set<IGameMatchX> getGameMatchXSet() {
        return getBaseStateAutomaton().getGameMatchXSet_();
    }

    @Override
    public void resetGameType() {
        getBaseStateAutomaton().setGameType_(getBaseStateAutomaton().getGameType_());
    }

    @Override
    public void setGameMatchX(IGameMatchX gameMatchX) {
        getBaseStateAutomaton().setGameMatchX_(gameMatchX);
    }
}
