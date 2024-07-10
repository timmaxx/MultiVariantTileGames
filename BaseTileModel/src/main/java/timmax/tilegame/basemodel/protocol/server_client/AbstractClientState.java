package timmax.tilegame.basemodel.protocol.server_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractClientState<Model> {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractClientState.class);

    private final ClientStateAutomaton<Model> clientStateAutomaton;

    public AbstractClientState(ClientStateAutomaton<Model> clientStateAutomaton) {
        this.clientStateAutomaton = clientStateAutomaton;
    }

    public ClientStateAutomaton<Model> getClientStateAutomaton() {
        return clientStateAutomaton;
    }
}
