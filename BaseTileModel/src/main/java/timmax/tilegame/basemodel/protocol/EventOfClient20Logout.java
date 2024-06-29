package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient20Logout<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton, ClientId clientId) {
        logger.debug("  onLogout");
        remoteClientStateAutomaton.forgetUserName();
    }

    @Override
    public String toString() {
        return "EventOfClient20Logout{}";
    }
}
