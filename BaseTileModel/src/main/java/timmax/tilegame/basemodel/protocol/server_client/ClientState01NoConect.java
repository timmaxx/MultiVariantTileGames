package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState01NoConect<Model, ClientId> extends AbstractClientState<Model, ClientId> implements IClientState01NoConect {
    public ClientState01NoConect(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
