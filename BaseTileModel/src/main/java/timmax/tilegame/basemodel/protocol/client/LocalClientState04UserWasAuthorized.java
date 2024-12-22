package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState04UserWasAuthorized;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class LocalClientState04UserWasAuthorized extends ClientState04UserWasAuthorized {
    public LocalClientState04UserWasAuthorized(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class State
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().updateOnAuthorizeUser();
    }

    //  class ClientState
    @Override
    public LocalClientStateAutomaton getBaseStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}
