package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState08GameMatchPlaying<Model> extends AbstractClientState<Model> implements IClientState08GameMatchPlaying {
    public ClientState08GameMatchPlaying(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    protected void setGameIsPlaying_(Boolean gameIsPlaying) {
        getClientStateAutomaton().setGameIsPlaying0(gameIsPlaying);
    }

    // interface IClientState08GameMatchPlaying
    @Override
    public Boolean getGameIsPlaying() {
        return getClientStateAutomaton().getGameIsPlaying0();
    }

    @Override
    public void forgetGameMatchPlaying() {
        setGameIsPlaying_(null);
    }
}
