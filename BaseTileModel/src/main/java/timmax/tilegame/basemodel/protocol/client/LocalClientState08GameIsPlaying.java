package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server_client.ClientState08GameIsPlaying;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

public abstract class LocalClientState08GameIsPlaying<Model, ClientId> extends ClientState08GameIsPlaying<Model, ClientId> {
    public LocalClientState08GameIsPlaying(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // ---- 7
    @Override
    public void forgetGameIsPlaying() {
        super.forgetGameIsPlaying();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnStopGameMatchPlaying();
    }
}
