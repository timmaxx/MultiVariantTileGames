package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public abstract class LocalClientState07GameMatchSelected<Model, ClientId> extends ClientState07GameMatchSelected<Model, ClientId> {
    public LocalClientState07GameMatchSelected(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

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
}