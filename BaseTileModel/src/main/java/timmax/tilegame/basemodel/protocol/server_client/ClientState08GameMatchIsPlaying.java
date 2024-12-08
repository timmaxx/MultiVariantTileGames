package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState08GameMatchIsPlaying<GameMatchX extends IGameMatchX> extends ClientState07GameMatchWasSet<GameMatchX> {
    public ClientState08GameMatchIsPlaying(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState08GameMatchPlaying
}
