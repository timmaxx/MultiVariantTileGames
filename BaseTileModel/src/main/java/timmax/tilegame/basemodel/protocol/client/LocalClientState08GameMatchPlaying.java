package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState08GameMatchPlaying;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class LocalClientState08GameMatchPlaying extends ClientState08GameMatchPlaying<GameMatchId> {
    public LocalClientState08GameMatchPlaying(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState99
    @Override
    public void doAfterTurnOn(){
        getClientStateAutomaton().updateOnSetGameMatchPlaying();
    }

    // class AbstractClientState
    // ---- 7
/*
    @Override
    public void forgetGameMatchPlaying() {
        super.forgetGameMatchPlaying();
        // getClientStateAutomaton().updateOnForgetGameMatchPlaying();
    }
*/
    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
