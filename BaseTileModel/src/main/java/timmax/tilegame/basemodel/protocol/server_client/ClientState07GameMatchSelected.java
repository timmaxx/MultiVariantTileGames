package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;

public abstract class ClientState07GameMatchSelected<GameMatchX extends IGameMatchX> extends ClientState06GameMatchSetSelected<GameMatchX> {
    public ClientState07GameMatchSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
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
    public GameMatchExtendedDto startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        return getClientStateAutomaton().startGameMatch_(gameMatchExtendedDto);
    }

    @Override
    public GameMatchStatus getGameMatchStatus() {
        return getClientStateAutomaton().getGameMatchStatus_();
    }
}
