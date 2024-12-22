package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState01NoConnect;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class LocalClientState01NoConnect extends ClientState01NoConnect {
    public LocalClientState01NoConnect(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class State
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().updateOnClose();
    }

    // class ClientState
    @Override
    public LocalClientStateAutomaton getBaseStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}
