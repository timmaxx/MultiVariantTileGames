package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState01NoConect<Model> extends AbstractClientState<Model> implements IClientState01NoConect {
    public ClientState01NoConect(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
