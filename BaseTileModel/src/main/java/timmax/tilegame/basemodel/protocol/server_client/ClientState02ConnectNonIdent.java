package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState02ConnectNonIdent<GameMatchX extends IGameMatchX> extends ClientState01NoConnect<GameMatchX> {
    public ClientState02ConnectNonIdent(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void authorizeUser(String userName) {
        getClientStateAutomaton().authorizeUser_(userName);
    }

    @Override
    public void close() {
        getClientStateAutomaton().close_();
    }
}
