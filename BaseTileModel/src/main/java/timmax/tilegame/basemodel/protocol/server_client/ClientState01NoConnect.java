package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState01NoConnect<GameMatchX extends IGameMatchX> extends ClientState<GameMatchX> {
    public ClientState01NoConnect(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void connect() {
        getBaseStateAutomaton().connect_();
    }

    @Override
    public Set<GameType> getGameTypeSet() {
        //  Warning:(25, 16) Unchecked assignment: 'java.util.Set' to 'java.util.Set<timmax.tilegame.basemodel.protocol.server.GameType>'. Reason: 'getBaseStateAutomaton()' has raw type, so result of getGameTypeSet_ is erased
        return getBaseStateAutomaton().getGameTypeSet_();
    }
}
