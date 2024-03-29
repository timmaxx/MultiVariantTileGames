package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public class EventOfClient50ForgetGameMatchSet extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientState<ClientId> remoteClientState) {
        logger.debug("  onForgetGameMatchSet");
        remoteClientState.forgetGameMatchSet();
    }

    @Override
    public String toString() {
        return "EventOfClient50ForgetGameMatchSet{}";
    }
}
