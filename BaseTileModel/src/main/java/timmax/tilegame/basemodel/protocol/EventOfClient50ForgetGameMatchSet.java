package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient50ForgetGameMatchSet<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientState) {
        logger.debug("  onForgetGameMatchSet");
        remoteClientState.forgetGameMatchSet();
    }

    @Override
    public String toString() {
        return "EventOfClient50ForgetGameMatchSet{}";
    }
}
