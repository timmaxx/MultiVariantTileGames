package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState03ConnectWithServerInfo;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class LocalClientState03ConnectWithServerInfo extends ClientState03ConnectWithServerInfo {
    public LocalClientState03ConnectWithServerInfo(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class State
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().updateOnOpen();
    }

    //  class ClientState
    @Override
    public LocalClientStateAutomaton getBaseStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}
