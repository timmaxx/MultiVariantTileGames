package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameMatch;
import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState06GameMatchSetSelected extends ClientState04GameTypeSetSelected {
    public ClientState06GameMatchSetSelected(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState06GameMatchSetSelected
    @Override
    public GameType getGameType() {
        return getClientStateAutomaton().getGameType_();
    }

    @Override
    public void reselectGameType() {
        GameType gameType = getClientStateAutomaton().getGameType_();
        selectGameType(gameType);
    }

    @Override
    public Set<GameMatch> getGameMatchSet() {
        return getClientStateAutomaton().getGameMatchSet_();
    }

    @Override
    public void selectGameMatch(GameMatch gameMatch) {
        getClientStateAutomaton().selectGameMatch_(gameMatch);
    }
}
