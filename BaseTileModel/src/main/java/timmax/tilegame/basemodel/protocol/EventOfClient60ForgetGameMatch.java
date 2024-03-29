package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public class EventOfClient60ForgetGameMatch extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientState<ClientId> remoteClientState) {
        logger.debug("  onForgetGameMatch");
        remoteClientState.forgetServerBaseModel();
    }

    @Override
    public String toString() {
        return "EventOfClient60ForgetGameMatch{}";
    }
}
