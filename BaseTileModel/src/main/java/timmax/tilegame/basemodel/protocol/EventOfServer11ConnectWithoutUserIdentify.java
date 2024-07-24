package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer11ConnectWithoutUserIdentify extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.openConnectWithoutUserIdentify();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
