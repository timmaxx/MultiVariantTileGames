package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState02ConnectWithoutServerInfo;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class LocalClientState02ConnectWithoutServerInfo extends ClientState02ConnectWithoutServerInfo<GameMatchDto> {
    public LocalClientState02ConnectWithoutServerInfo(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class ClientState
    @Override
    public LocalClientStateAutomaton getBaseStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}
