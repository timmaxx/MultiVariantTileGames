package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

//  ToDo: Переименовать в EventOfServer11ConnectWithoutUserIdentify
public class EventOfServer11OpenConnectWithoutUserIdentify extends EventOfServer {
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.connect();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{}";
    }
}
