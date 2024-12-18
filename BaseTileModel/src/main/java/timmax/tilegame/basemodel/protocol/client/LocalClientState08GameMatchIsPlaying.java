package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState08GameMatchIsPlaying;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchDto;

public class LocalClientState08GameMatchIsPlaying extends ClientState08GameMatchIsPlaying<GameMatchDto> {
    public LocalClientState08GameMatchIsPlaying(ClientStateAutomaton<GameMatchDto> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    //  class State
    @Override
    public void doAfterTurnOn() {
        getBaseStateAutomaton().updateOnSetGameMatchIsPlaying();
    }

    // class ClientState
    @Override
    public LocalClientStateAutomaton getBaseStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getBaseStateAutomaton());
    }
}
