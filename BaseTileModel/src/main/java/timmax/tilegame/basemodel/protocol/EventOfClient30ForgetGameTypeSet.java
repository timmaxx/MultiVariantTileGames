package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public class EventOfClient30ForgetGameTypeSet extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientState<ClientId> remoteClientState) {
        logger.debug("  onForgetGameTypeSet");
        remoteClientState.forgetGameTypeSet();
    }

    @Override
    public String toString() {
        return "EventOfClient30ForgetGameTypeSet{}";
    }
}
