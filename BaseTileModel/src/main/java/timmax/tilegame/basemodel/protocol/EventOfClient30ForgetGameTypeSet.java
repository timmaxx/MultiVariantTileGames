package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient30ForgetGameTypeSet<ClientId> extends EventOfClient<ClientId> {
    @Override
    public void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientState) {
        logger.debug("  onForgetGameTypeSet");
        remoteClientState.forgetGameTypeSet();
    }

    @Override
    public String toString() {
        return "EventOfClient30ForgetGameTypeSet{}";
    }
}
