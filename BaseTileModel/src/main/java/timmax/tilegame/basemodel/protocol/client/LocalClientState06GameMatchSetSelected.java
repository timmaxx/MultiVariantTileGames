package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class LocalClientState06GameMatchSetSelected extends ClientState06GameMatchSetSelected<GameMatchId> {
    public LocalClientState06GameMatchSetSelected(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 5 (Набор моделей игр)
    @Override
    public void forgetGameMatchSet() {
        super.forgetGameMatchSet();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnForgetGameMatchSet();
    }

    // ---- 6 (Конкретная модель игры)
    @Override
    public void setGameMatch(GameMatchId gameMatchId) {
        super.setGameMatch(gameMatchId);
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnSetGameMatch();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
