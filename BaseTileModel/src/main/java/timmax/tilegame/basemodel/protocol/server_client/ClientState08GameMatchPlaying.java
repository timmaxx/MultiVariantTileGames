package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState08GameMatchPlaying<GameMatchX extends IGameMatchX> extends ClientState07GameMatchSelected<GameMatchX> {
    public ClientState08GameMatchPlaying(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState08GameMatchPlaying
    @Override
    public Boolean getGameIsPlaying() {
        return getClientStateAutomaton().getGameIsPlaying_();
    }
}
