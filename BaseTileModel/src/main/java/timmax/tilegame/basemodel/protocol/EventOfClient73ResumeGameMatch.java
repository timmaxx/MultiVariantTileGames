package timmax.tilegame.basemodel.protocol;

import timmax.tilegame.basemodel.protocol.server.RemoteClientStateAutomaton;

public class EventOfClient73ResumeGameMatch extends EventOfClient {
    public EventOfClient73ResumeGameMatch() {
        super();
    }

    // class EventOfClient
    @Override
    public <ClientId> void executeOnServer(RemoteClientStateAutomaton<ClientId> remoteClientStateAutomaton) {
        remoteClientStateAutomaton.resumeGameMatch();
    }

    // class Object
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                '}';
    }
}
