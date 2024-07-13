package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.ClientState02ConnectNonIdent;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

import java.util.Set;

public class LocalClientState02ConnectNonIdent extends ClientState02ConnectNonIdent<GameMatchId> {
    public LocalClientState02ConnectNonIdent(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 2 (Пользователь)
    @Override
    public void setUser(String userName, Set<GameType> gameTypeSet) {
        super.setUser(userName, gameTypeSet);
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnSetUser();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
