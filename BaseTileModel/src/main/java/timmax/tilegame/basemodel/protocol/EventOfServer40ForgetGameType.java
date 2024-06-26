package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer40ForgetGameType<Model> extends EventOfServer<Model> {
    @Override
    public void executeOnClient(LocalClientStateAutomaton<Model> localClientStateAutomaton) {
        logger.debug("  onForgetGameType");
        localClientStateAutomaton.forgetGameType();
    }

    @Override
    public String toString() {
        return "EventOfServer40ForgetGameType{}";
    }
}
