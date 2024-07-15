package timmax.tilegame.basemodel.protocol.server_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractClientState<GameMatchX extends IGameMatchX> {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractClientState.class);

    private final ClientStateAutomaton<GameMatchX> clientStateAutomaton;

    public AbstractClientState(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        this.clientStateAutomaton = clientStateAutomaton;
    }

    public ClientStateAutomaton<GameMatchX> getClientStateAutomaton() {
        return clientStateAutomaton;
    }
}
