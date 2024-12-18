package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState03ConnectWithServerInfo<GameMatchX extends IGameMatchX> extends ClientState02ConnectWithoutServerInfo<GameMatchX> {
    public ClientState03ConnectWithServerInfo(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void authorizeUser(String userName) {
        getBaseStateAutomaton().authorizeUser_(userName);
    }
}
