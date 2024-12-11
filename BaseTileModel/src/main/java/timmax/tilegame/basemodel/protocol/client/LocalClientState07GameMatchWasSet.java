package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchWasSet;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class LocalClientState07GameMatchWasSet extends ClientState07GameMatchWasSet<GameMatchDto> {
    public LocalClientState07GameMatchWasSet(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().updateOnSetGameMatch();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getBaseStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}
