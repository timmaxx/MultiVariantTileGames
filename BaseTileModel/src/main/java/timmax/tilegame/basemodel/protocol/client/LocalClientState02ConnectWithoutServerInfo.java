package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState02ConnectWithoutServerInfo;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class LocalClientState02ConnectWithoutServerInfo extends ClientState02ConnectWithoutServerInfo {
    public LocalClientState02ConnectWithoutServerInfo(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class ClientState
    @Override
    public LocalClientStateAutomaton getBaseStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}
