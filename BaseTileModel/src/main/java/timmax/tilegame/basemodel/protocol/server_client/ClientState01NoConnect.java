package timmax.tilegame.basemodel.protocol.server_client;

public abstract class ClientState01NoConnect<Model> extends AbstractClientState<Model> implements IClientState01NoConnect {
    public ClientState01NoConnect(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
