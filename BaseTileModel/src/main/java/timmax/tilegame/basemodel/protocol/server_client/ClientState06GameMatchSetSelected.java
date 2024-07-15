package timmax.tilegame.basemodel.protocol.server_client;

import java.util.HashSet;
import java.util.Set;

public abstract class ClientState06GameMatchSetSelected<GameMatchX extends IGameMatchX> extends AbstractClientState<GameMatchX> implements IClientState06GameMatchSetSelected<GameMatchX> {
    public ClientState06GameMatchSetSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public Set<GameMatchX> getGameMatchXSet() {
        return getClientStateAutomaton().getGameMatchXSet_();
    }

    @Override
    public void forgetGameMatchXSet() {
        getClientStateAutomaton().setGameMatchXSet_(new HashSet<>());
    }

    @Override
    public void setGameMatchX(GameMatchX gameMatchX) {
        getClientStateAutomaton().setGameMatchX_(gameMatchX);
    }
}
