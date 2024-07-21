package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState02ConnectNonIdent;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class LocalClientState02ConnectNonIdent extends ClientState02ConnectNonIdent<GameMatchId> {
    public LocalClientState02ConnectNonIdent(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState99
    @Override
    public void doAfterTurnOn() {
        // getClientStateAutomaton().updateOnSetUser();
        getClientStateAutomaton().updateOnOpen();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
