package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

public abstract class ClientState04GameTypeSetSelected<GameMatchX extends IGameMatchX> extends ClientState02ConnectNonIdent<GameMatchX> {
    public ClientState04GameTypeSetSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState04GameTypeSetSelected
    @Override
    //      Warning:(14, 12) Raw use of parameterized class 'GameType'
    public GameType getGameType() {
        return getClientStateAutomaton().getGameType_();
    }

    @Override
    public void reauthorizeUser() {
        String userName = getClientStateAutomaton().getUserName_();
        authorizeUser(userName);
    }

    @Override
    public void setGameType(GameType gameType) {
        getClientStateAutomaton().setGameType_(gameType);
    }
}
