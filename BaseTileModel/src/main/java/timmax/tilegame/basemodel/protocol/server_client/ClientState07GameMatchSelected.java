package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState07GameMatchSelected<GameMatchX extends IGameMatchX> extends ClientState06GameMatchSetSelected<GameMatchX> {
    public ClientState07GameMatchSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState07GameMatchSelected
    @Override
    public void reselectGameMatch() {
        GameMatchX gameMatchX = getClientStateAutomaton().getGameMatchX_();
        selectGameMatchX(gameMatchX);
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
    public void resumeGameMatch() {
        getClientStateAutomaton().resumeGameMatch_();
    }

    @Override
    public Boolean getGameMatchIsPlaying() {
        return getClientStateAutomaton().getGameMatchIsPlaying_();
    }
}
