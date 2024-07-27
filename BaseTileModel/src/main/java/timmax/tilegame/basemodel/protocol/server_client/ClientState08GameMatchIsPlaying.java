package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState08GameMatchIsPlaying<GameMatchX extends IGameMatchX> extends ClientState07GameMatchSelected<GameMatchX> {
    public ClientState08GameMatchIsPlaying(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState08GameMatchPlaying
    @Override
    public Boolean getGameMatchIsPlaying() {
        return getClientStateAutomaton().getGameIsPlaying_();
    }
}
