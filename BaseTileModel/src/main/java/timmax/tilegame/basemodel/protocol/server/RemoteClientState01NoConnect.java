package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.ClientState01NoConnect;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class RemoteClientState01NoConnect extends ClientState01NoConnect {
    public RemoteClientState01NoConnect(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }
}
