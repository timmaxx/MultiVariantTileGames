package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;

public abstract class ClientState07GameMatchWasSet<GameMatchX extends IGameMatchX> extends ClientState06GameTypeWasSet<GameMatchX> {
    public ClientState07GameMatchWasSet(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState07GameMatchSelected
    @Override
    public void resetGameMatch() {
        GameMatchX gameMatchX = getClientStateAutomaton().getGameMatchX_();
        setGameMatchX(gameMatchX);
    }

    @Override
    public GameMatchX getGameMatchX() {
        return getClientStateAutomaton().getGameMatchX_();
    }

    @Override
    public void startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        getClientStateAutomaton().startGameMatch_(gameMatchExtendedDto);
    }

    @Override
    public GameMatchStatus getGameMatchStatus() {
        return getClientStateAutomaton().getGameMatchStatus_();
    }
}
