package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState06GameMatchSetSelected<GameMatchX extends IGameMatchX> extends ClientState04GameTypeSetSelected<GameMatchX> {
    public ClientState06GameMatchSetSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    //      Warning:(14, 12) Raw use of parameterized class 'GameType'
    public GameType getGameType() {
        return getClientStateAutomaton().getGameType_();
    }

    @Override
    public void reselectGameType() {
        GameType gameType = getClientStateAutomaton().getGameType_();
        selectGameType(gameType);
    }

    @Override
    public Set<GameMatchX> getGameMatchXSet() {
        return getClientStateAutomaton().getGameMatchXSet_();
    }

    @Override
    public void selectGameMatchX(GameMatchX gameMatchX) {
        getClientStateAutomaton().selectGameMatchX_(gameMatchX);
    }
}
