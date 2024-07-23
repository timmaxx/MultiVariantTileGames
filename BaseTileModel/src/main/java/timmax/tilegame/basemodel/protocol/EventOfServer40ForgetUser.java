package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer40ForgetUser extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.changeStateTo02ConnectNonIdent();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}