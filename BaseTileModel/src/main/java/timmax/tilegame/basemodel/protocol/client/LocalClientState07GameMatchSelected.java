package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

public class LocalClientState07GameMatchSelected extends ClientState07GameMatchSelected<GameMatchId> {
    public LocalClientState07GameMatchSelected(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 6 (Конкретная модель игры)
    @Override
    public void forgetGameMatchX() {
        super.forgetGameMatchX();
        // ToDo: Сделать updateOn...() доступным напрямую?
        getClientStateAutomaton().getObserverOnAbstractEventHashSet().updateOnForgetGameMatch();
    }

    // ---- 7
    @Override
    public void setGameMatchPlaying(Boolean gameIsPlaying) {
        super.setGameMatchPlaying(gameIsPlaying);
        // ToDo: Сделать updateOn...() доступным напрямую?
        getClientStateAutomaton().getObserverOnAbstractEventHashSet().updateOnSetGameMatchPlaying();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
