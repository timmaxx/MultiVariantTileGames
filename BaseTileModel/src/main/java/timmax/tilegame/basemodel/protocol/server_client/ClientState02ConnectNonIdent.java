package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState02ConnectNonIdent<GameMatchX extends IGameMatchX> extends ClientState01NoConnect<GameMatchX> {
    public ClientState02ConnectNonIdent(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public void authorizeUser(String userName) {
        getBaseStateAutomaton().authorizeUser_(userName);
    }

    @Override
    public void close() {
        getBaseStateAutomaton().close_();
    }

    @Override
    public Set<GameType> getGameTypeSet() {
        //  Warning:(25, 16) Unchecked assignment: 'java.util.Set' to 'java.util.Set<timmax.tilegame.basemodel.protocol.server.GameType>'. Reason: 'getBaseStateAutomaton()' has raw type, so result of getGameTypeSet_ is erased
        return getBaseStateAutomaton().getGameTypeSet_();
    }
}
