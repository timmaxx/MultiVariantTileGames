package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState06GameMatchSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class LocalClientState06GameMatchSetSelected extends ClientState06GameMatchSetSelected<GameMatchId> {
    public LocalClientState06GameMatchSetSelected(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 4 (Конкретный тип игры)
    @Override
    public void forgetGameMatchXSet() {
        super.forgetGameMatchXSet();
        // ToDo: Сделать updateOn...() доступным напрямую?
        getClientStateAutomaton().getObserverOnAbstractEventHashSet().updateOnForgetGameMatchSet();
    }

    // ---- 6 (Конкретная модель игры)
    @Override
    public void setGameMatchX(GameMatchId gameMatchX) {
        super.setGameMatchX(gameMatchX);
        // ToDo: Сделать updateOn...() доступным напрямую?
        getClientStateAutomaton().getObserverOnAbstractEventHashSet().updateOnSetGameMatch();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
