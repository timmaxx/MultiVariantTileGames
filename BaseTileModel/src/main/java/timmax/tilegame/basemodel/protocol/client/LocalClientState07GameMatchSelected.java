package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class LocalClientState07GameMatchSelected extends ClientState07GameMatchSelected<GameMatchDto> {
    public LocalClientState07GameMatchSelected(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void doAfterTurnOn() {
        getClientStateAutomaton().updateOnSetGameMatch();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
