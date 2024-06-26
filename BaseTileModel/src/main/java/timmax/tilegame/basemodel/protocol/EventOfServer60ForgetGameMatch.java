package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer60ForgetGameMatch<Model> extends EventOfServer<Model> {
    @Override
    public void executeOnClient(LocalClientStateAutomaton<Model> localClientStateAutomaton) {
        logger.debug("  onForgetGameMatch");
        localClientStateAutomaton.forgetServerBaseModel();
    }

    @Override
    public String toString() {
        return "EventOfServer60ForgetGameMatch{}";
    }
}
