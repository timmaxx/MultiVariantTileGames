package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer60ForgetGameMatch extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        logger.debug("  onForgetGameMatch");
        localClientStateAutomaton.forgetGameMatch();
    }

    @Override
    public String toString() {
        return "EventOfServer60ForgetGameMatch{}";
    }
}
