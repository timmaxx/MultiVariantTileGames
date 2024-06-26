package timmax.tilegame.basemodel.protocol.server_client;

public abstract class AbstractClientState<Model> {
    private final ClientStateAutomaton<Model> clientStateAutomaton;

    public AbstractClientState(ClientStateAutomaton<Model> clientStateAutomaton) {
        this.clientStateAutomaton = clientStateAutomaton;
    }

    public ClientStateAutomaton<Model> getClientStateAutomaton() {
        return clientStateAutomaton;
    }
}
