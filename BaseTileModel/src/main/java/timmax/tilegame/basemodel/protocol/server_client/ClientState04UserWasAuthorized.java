package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

public abstract class ClientState04UserWasAuthorized<GameMatchX extends IGameMatchX> extends ClientState03ConnectWithServerInfo<GameMatchX> {
    public ClientState04UserWasAuthorized(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState04GameTypeSetSelected
    @Override
    //      Warning:(14, 12) Raw use of parameterized class 'GameType'
    public GameType getGameType() {
        return getBaseStateAutomaton().getGameType_();
    }

    @Override
    public void reauthorizeUser() {
        getBaseStateAutomaton().authorizeUser_(getBaseStateAutomaton().getUserName_());
    }

    @Override
    public void setGameType(GameType gameType) {
        getBaseStateAutomaton().setGameType_(gameType);
    }
}
