package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState01NoConnect;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class LocalClientState01NoConnect extends ClientState01NoConnect<GameMatchId> {
    public LocalClientState01NoConnect(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void doAfterTurnOn(){
        getClientStateAutomaton().updateOnClose();
    }
/*
    // 2 interface IClientState01NoConnect

    @Override
    public void changeStateTo02ConnectNonIdent() {
        // getClientStateAutomaton().updateOnOpen();
        super.changeStateTo02ConnectNonIdent();
    }
*/
    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
