package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer50ForgetGameMatchSet extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.forgetGameMatchSet();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
