package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer60ForgetGameMatch extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.forgetGameMatch();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
