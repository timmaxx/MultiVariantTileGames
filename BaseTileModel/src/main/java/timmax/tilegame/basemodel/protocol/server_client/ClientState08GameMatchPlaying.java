package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState08GameMatchPlaying<GameMatchX extends IGameMatchX> extends AbstractClientState<GameMatchX> implements IClientState08GameMatchPlaying {
    public ClientState08GameMatchPlaying(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState08GameMatchPlaying
    @Override
    public Boolean getGameIsPlaying() {
        return getClientStateAutomaton().getGameIsPlaying_();
    }

    @Override
    public void forgetGameMatchPlaying() {
        getClientStateAutomaton().setGameIsPlaying_(null);
    }
}
