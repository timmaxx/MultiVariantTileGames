package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class LocalClientState07GameMatchSelected<Model> extends ClientState07GameMatchSelected<Model> {
    public LocalClientState07GameMatchSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 6 (Конкретная модель игры)
    @Override
    public void forgetServerBaseModel() {
        super.forgetServerBaseModel();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnForgetGameMatch();
    }

    // ---- 7
    @Override
    public void setGameIsPlaying(Boolean gameIsPlaying) {
        super.setGameIsPlaying(gameIsPlaying);
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnStartGameMatchPlaying();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton<Model> getClientStateAutomaton() {
        return (LocalClientStateAutomaton<Model>)(super.getClientStateAutomaton());
    }
}
