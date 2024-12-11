package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState02ConnectNonIdent;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class LocalClientState02ConnectNonIdent extends ClientState02ConnectNonIdent<GameMatchDto> {
    public LocalClientState02ConnectNonIdent(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState99
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().updateOnOpen();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getBaseStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}
