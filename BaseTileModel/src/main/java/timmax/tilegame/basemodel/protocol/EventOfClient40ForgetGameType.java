package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient40ForgetGameType<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientState, ClientId clientId) {
        logger.debug("  onForgetGameType");
        remoteClientState.forgetGameType();
    }

    @Override
    public String toString() {
        return "EventOfClient40ForgetGameType{}";
    }
}
