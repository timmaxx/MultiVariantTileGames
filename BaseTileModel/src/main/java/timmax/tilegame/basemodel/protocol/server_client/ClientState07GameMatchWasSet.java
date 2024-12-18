package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;

public abstract class ClientState07GameMatchWasSet<GameMatchX extends IGameMatchX> extends ClientState06GameTypeWasSet<GameMatchX> {
    public ClientState07GameMatchWasSet(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState07GameMatchSelected
    @Override
    public void resetGameMatch() {
        getBaseStateAutomaton().setGameMatchX_(getBaseStateAutomaton().getGameMatchX_());
    }

    @Override
    public GameMatchX getGameMatchX() {
        return getBaseStateAutomaton().getGameMatchX_();
    }

    @Override
    public void startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        getBaseStateAutomaton().startGameMatch_(gameMatchExtendedDto);
    }

    @Override
    public GameMatchStatus getGameMatchStatus() {
        return getBaseStateAutomaton().getGameMatchStatus_();
    }
}
