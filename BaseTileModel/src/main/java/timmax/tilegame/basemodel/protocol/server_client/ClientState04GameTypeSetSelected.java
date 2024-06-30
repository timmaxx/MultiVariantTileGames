package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState04GameTypeSetSelected<Model> extends AbstractClientState<Model> implements IClientState04GameTypeSetSelected {
    public ClientState04GameTypeSetSelected(ClientStateAutomaton<Model> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState04GameTypeSetSelected
    @Override
    public Set<GameType> getGameTypeSet() {
        return getClientStateAutomaton().getGameTypeSet0();
    }

    @Override
    public void forgetGameTypeSet() {
        getClientStateAutomaton().setGameTypeSet_(null);
    }

    @Override
    public void setGameType(GameType gameType) {
        getClientStateAutomaton().setGameType_(gameType);
    }
}
