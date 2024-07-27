package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class LocalClientState07GameMatchSelected extends ClientState07GameMatchSelected<GameMatchId> {
    public LocalClientState07GameMatchSelected(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void resetGameMatchX() {
        GameMatchId gameMatchX = getClientStateAutomaton().getGameMatchX();
        setGameMatchX(gameMatchX);
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
