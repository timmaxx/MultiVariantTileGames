package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer20Logout extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        logger.debug("  onLogout");
        localClientStateAutomaton.forgetUser();
    }

    @Override
    public String toString() {
        return "EventOfServer20Logout{}";
    }
}
