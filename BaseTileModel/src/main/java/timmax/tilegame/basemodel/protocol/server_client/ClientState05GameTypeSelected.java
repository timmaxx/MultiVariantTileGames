package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState05GameTypeSelected<Model> extends AbstractClientState<Model> implements IClientState05GameTypeSelected<Model> {
    public ClientState05GameTypeSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState05GameTypeSelected
    @Override
    public GameType getGameType() {
        return getClientStateAutomaton().getGameType0();
    }

    @Override
    public void forgetGameType() {
        getClientStateAutomaton().setGameType_(null);
    }

    @Override
    public void setGameMatchXSet(Set<Model> gameMatchXSet) {
        getClientStateAutomaton().setGameMatchXSet_(gameMatchXSet);
    }
}
