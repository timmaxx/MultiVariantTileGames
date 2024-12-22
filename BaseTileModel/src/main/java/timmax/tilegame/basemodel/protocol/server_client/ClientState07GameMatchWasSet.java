package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.GameMatchStatus;

public abstract class ClientState07GameMatchWasSet extends ClientState06GameTypeWasSet {
    public ClientState07GameMatchWasSet(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState07GameMatchSelected
    @Override
    public IGameMatchX getGameMatchX() {
        return getBaseStateAutomaton().getGameMatchX_();
    }

    @Override
    public void resetGameMatch() {
        getBaseStateAutomaton().setGameMatchX_(getBaseStateAutomaton().getGameMatchX_());
    }

    @Override
    public void startGameMatch(GameMatchExtendedDto gameMatchExtendedDto) {
        getBaseStateAutomaton().startGameMatch_(gameMatchExtendedDto);
    }

    //  ToDo:   Ещё один геттер - не единообразно с предыдущими ClientState0X...
    @Override
    public GameMatchStatus getGameMatchStatus() {
        return getBaseStateAutomaton().getGameMatchStatus_();
    }
}
