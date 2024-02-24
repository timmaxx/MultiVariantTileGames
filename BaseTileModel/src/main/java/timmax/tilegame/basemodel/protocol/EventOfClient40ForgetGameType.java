package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientState;

public class EventOfClient40ForgetGameType extends EventOfClient {
    @Override
    public <ClientId> void executeOnServer(RemoteClientState<ClientId> remoteClientState) {
        System.out.println("  onForgetGameType");
        remoteClientState.forgetGameType();
    }

    @Override
    public String toString() {
        return "EventOfClient40ForgetGameType{}";
    }
}
