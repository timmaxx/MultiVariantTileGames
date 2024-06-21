package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState08GameIsPlaying;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public class LocalClientState08GameIsPlaying<Model> extends ClientState08GameIsPlaying<Model> {
    public LocalClientState08GameIsPlaying(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 7
    @Override
    public void forgetGameIsPlaying() {
        super.forgetGameIsPlaying();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnStopGameMatchPlaying();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton<Model> getClientStateAutomaton() {
        return (LocalClientStateAutomaton<Model>)(super.getClientStateAutomaton());
    }
}
