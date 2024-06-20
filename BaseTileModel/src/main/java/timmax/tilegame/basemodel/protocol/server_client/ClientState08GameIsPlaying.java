package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState08GameIsPlaying<Model, ClientId> extends AbstractClientState<Model, ClientId> implements IClientState08GameIsPlaying {
    protected Boolean gameIsPlaying; // ---- 7 (Партия была начата)

    public ClientState08GameIsPlaying(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    protected void setGameIsPlaying_(Boolean gameIsPlaying) {
        this.gameIsPlaying = gameIsPlaying;
    }

    // interface IClientState08GameIsPlaying
    @Override
    public Boolean getGameIsPlaying() {
        return gameIsPlaying;
    }

    @Override
    public void forgetGameIsPlaying() {
        setGameIsPlaying_(null);
    }
}
