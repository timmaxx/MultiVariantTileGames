package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState07GameMatchSelected<GameMatchX extends IGameMatchX> extends ClientState06GameMatchSetSelected<GameMatchX> {
    public ClientState07GameMatchSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState07GameMatchSelected
    @Override
    public void resetGameMatchX() {
        GameMatchX gameMatchX = getClientStateAutomaton().getGameMatchX_();
        setGameMatchX(gameMatchX);
    }

    @Override
    public GameMatchX getGameMatchX() {
        return getClientStateAutomaton().getGameMatchX_();
    }

    @Override
    public void setGameMatchIsPlaying(Boolean gameMatchIsPlaying) {
        getClientStateAutomaton().setGameMatchIsPlaying_(gameMatchIsPlaying);
    }
}
