package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient60ForgetGameMatch<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        remoteClientStateAutomaton.forgetGameMatch();
    }

    @Override
    public String toString() {
        return "EventOfClient60ForgetGameMatch{}";
    }
}
