package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState07GameMatchSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;

public class LocalClientState07GameMatchSelected extends ClientState07GameMatchSelected<InstanceIdOfModel> {
    public LocalClientState07GameMatchSelected(ClientStateAutomaton<InstanceIdOfModel> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 6 (Конкретная модель игры)
    @Override
    public void forgetGameMatch() {
        super.forgetGameMatch();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnForgetGameMatch();
    }

    // ---- 7
    @Override
    public void setGameMatchPlaying(Boolean gameIsPlaying) {
        super.setGameMatchPlaying(gameIsPlaying);
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnSetGameMatchPlaying();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
