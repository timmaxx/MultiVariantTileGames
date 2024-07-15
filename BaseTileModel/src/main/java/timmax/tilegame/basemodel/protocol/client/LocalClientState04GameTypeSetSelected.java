package timmax.tilegame.basemodel.protocol.client;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;

import java.util.Set;

public class LocalClientState04GameTypeSetSelected extends ClientState04GameTypeSetSelected<GameMatchId> {
    public LocalClientState04GameTypeSetSelected(ClientStateAutomaton<GameMatchId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 3 (Список типов игр)
    @Override
    public void forgetGameTypeSet() {
        super.forgetGameTypeSet();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnForgetGameTypeSet();
    }

    // ---- 4 (Конкретный тип игры)
    @Override
    public void setGameType(GameType gameType, Set<GameMatchId> gameMatchXSet) {
        super.setGameType(gameType, gameMatchXSet);
        getClientStateAutomaton().getMapOfViewName_View().clear();
        getClientStateAutomaton().getHashSetOfObserverOnAbstractEvent().updateOnSetGameType();
    }

    // class AbstractClientState
    @Override
    public LocalClientStateAutomaton getClientStateAutomaton() {
        return (LocalClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
