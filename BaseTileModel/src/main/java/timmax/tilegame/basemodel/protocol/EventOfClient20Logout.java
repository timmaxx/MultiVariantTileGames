package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public class EventOfClient20Logout extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientState<ClientId> remoteClientState) {
        logger.debug("  onLogout");
        remoteClientState.forgetUserName();
    }

    @Override
    public String toString() {
        return "EventOfClient20Logout{}";
    }
}
