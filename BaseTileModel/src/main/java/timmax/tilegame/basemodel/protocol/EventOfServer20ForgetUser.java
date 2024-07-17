package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer20ForgetUser extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.forgetUser();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
