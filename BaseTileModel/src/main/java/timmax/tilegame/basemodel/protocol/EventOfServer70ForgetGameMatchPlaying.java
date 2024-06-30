package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer70ForgetGameMatchPlaying extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.forgetGameMatchPlaying();
    }

    @Override
    public String toString() {
        return "EventOfServer70ForgetGameMatchPlaying{}";
    }
}
