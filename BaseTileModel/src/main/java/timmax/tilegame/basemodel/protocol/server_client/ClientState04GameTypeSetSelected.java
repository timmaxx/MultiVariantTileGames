package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.Set;

public abstract class ClientState04GameTypeSetSelected extends ClientState02ConnectNonIdent {
    public ClientState04GameTypeSetSelected(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState04GameTypeSetSelected
    @Override
    public void reauthorizeUser() {
        String userName = getClientStateAutomaton().getUserName_();
        Set<GameType> gameTypeSet = getClientStateAutomaton().getGameTypeSet_();
        authorizeUser(userName, gameTypeSet);
    }

    @Override
    public Set<GameType> getGameTypeSet() {
        return getClientStateAutomaton().getGameTypeSet_();
    }

    @Override
    public void selectGameType(GameType gameType) {
        getClientStateAutomaton().selectGameType_(gameType);
    }
}
