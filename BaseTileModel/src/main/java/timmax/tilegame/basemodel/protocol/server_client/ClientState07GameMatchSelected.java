package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;
import timmax.tilegame.basemodel.protocol.server.GameMatch;

public abstract class ClientState07GameMatchSelected extends ClientState06GameMatchSetSelected {
    public ClientState07GameMatchSelected(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState07GameMatchSelected
    @Override
    public void reselectGameMatch() {
        GameMatch gameMatch = getClientStateAutomaton().getGameMatch_();
        this.selectGameMatch(gameMatch);
    }

    @Override
    public GameMatch getGameMatch() {
        return getClientStateAutomaton().getGameMatch_();
    }

    @Override
    public GameMatch startGameMatch(GameMatch gameMatch) {
        return getClientStateAutomaton().startGameMatch_(gameMatch);
    }

    @Override
    public void resumeGameMatch() {
        getClientStateAutomaton().resumeGameMatch_();
    }

    @Override
    public GameMatchStatus getGameMatchStatus() {
        return getClientStateAutomaton().getGameMatchStatus_();
    }
}
