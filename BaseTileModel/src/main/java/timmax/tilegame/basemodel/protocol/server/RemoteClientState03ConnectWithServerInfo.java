package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.ClientState03ConnectWithServerInfo;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState03ConnectWithServerInfo extends ClientState03ConnectWithServerInfo {
    public RemoteClientState03ConnectWithServerInfo(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class ClientState
    @Override
    public RemoteClientStateAutomaton getBaseStateAutomaton() {
        return (RemoteClientStateAutomaton)(super.getBaseStateAutomaton());
    }
}
