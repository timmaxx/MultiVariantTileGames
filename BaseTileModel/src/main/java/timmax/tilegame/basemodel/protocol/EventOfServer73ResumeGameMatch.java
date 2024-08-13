package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;

public class EventOfServer73ResumeGameMatch extends EventOfServer {
    public EventOfServer73ResumeGameMatch() {
        super();
    }

    // class EventOfServer
    @Override
    public void executeOnClient(LocalClientStateAutomaton localClientStateAutomaton) {
        localClientStateAutomaton.resumeGameMatch(/*paramsOfModelValueMap*/);
    }

    // class Object
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                '}';
    }
}
