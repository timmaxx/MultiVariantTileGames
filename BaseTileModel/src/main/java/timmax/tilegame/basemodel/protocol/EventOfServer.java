package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

// ToDo: Устранить параметризацию от Model
public abstract class EventOfServer extends Event {
    public abstract void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton);

    @Override
    public String toString() {
        return "EventOfServer{}";
    }
}
