package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState03ConnectWithServerInfo<GameMatchX extends IGameMatchX> extends ClientState02ConnectWithoutServerInfo<GameMatchX> {
    public ClientState03ConnectWithServerInfo(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState02ConnectNonIdent
    @Override
    public Set<GameType> getGameTypeSet() {
        //  Warning:(25, 16) Unchecked assignment: 'java.util.Set' to 'java.util.Set<timmax.tilegame.basemodel.protocol.server.GameType>'. Reason: 'getBaseStateAutomaton()' has raw type, so result of getGameTypeSet_ is erased
        return getBaseStateAutomaton().getGameTypeSet_();
    }

    @Override
    public void authorizeUser(String userName) {
        getBaseStateAutomaton().authorizeUser_(userName);
    }
}
