package timmax.tilegame.basemodel.protocol.server_client;

public abstract class AbstractClientState<Model, ClientId> {
    private final ClientStateAutomaton<Model, ClientId> clientStateAutomaton;

    public AbstractClientState(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        this.clientStateAutomaton = clientStateAutomaton;
    }

    public ClientStateAutomaton<Model, ClientId> getClientStateAutomaton() {
        return clientStateAutomaton;
    }
}
