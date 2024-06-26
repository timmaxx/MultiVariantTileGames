package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer50ForgetGameMatchSet<Model> extends EventOfServer<Model> {
    @Override
    public void executeOnClient(LocalClientStateAutomaton<Model> localClientStateAutomaton) {
        logger.debug("  onForgetGameMatchSet");
        localClientStateAutomaton.forgetGameMatchSet();
    }

    @Override
    public String toString() {
        return "EventOfServer50ForgetGameMatchSet{}";
    }
}
