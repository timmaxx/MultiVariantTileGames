package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient50ForgetGameMatchSet<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        logger.debug("  onForgetGameMatchSet");
        remoteClientStateAutomaton.forgetGameMatchSet();
    }

    @Override
    public String toString() {
        return "EventOfClient50ForgetGameMatchSet{}";
    }
}
