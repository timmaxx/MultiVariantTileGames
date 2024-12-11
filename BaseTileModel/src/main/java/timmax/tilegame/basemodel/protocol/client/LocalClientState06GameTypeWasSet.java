package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameTypeWasSet;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class LocalClientState06GameTypeWasSet extends ClientState06GameTypeWasSet<GameMatchDto> {
    public LocalClientState06GameTypeWasSet(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState99
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().clearViewName_ViewMap();
        getBaseStateAutomaton().updateOnSetGameType();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getBaseStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}
