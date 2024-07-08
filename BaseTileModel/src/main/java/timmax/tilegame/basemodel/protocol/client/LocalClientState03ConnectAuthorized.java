package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.ClientState03ConnectAuthorized;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

import java.util.Set;

public class LocalClientState03ConnectAuthorized extends ClientState03ConnectAuthorized<GameMatchId> {
    public LocalClientState03ConnectAuthorized(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 2
    @Override
    public void forgetUser() {
        super.forgetUser();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnForgetUser();
    }

    // ---- 3
    @Override
    public void setGameTypeSet(Set<GameType> gameTypeSet) {
        super.setGameTypeSet(gameTypeSet);
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnSetGameTypeSet();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
