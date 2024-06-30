package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer30ForgetGameTypeSet extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.forgetGameTypeSet();
    }

    @Override
    public String toString() {
        return "EventOfServer30ForgetGameTypeSet{}";
    }
}
