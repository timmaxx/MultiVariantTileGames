package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState02ConnectNonIdent<Model, ClientId> extends AbstractClientState<Model, ClientId> implements IClientState02ConnectNonIdent {
    public ClientState02ConnectNonIdent(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void setUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new NullPointerException("UserName is null. It must be not null for this method.");
        }
        getClientStateAutomaton().clientState03ConnectAuthorized.setUserName_(userName);
    }
}
