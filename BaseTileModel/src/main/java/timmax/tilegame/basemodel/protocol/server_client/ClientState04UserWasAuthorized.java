package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

public abstract class ClientState04UserWasAuthorized extends ClientState03ConnectWithServerInfo {
    public ClientState04UserWasAuthorized(ClientStateAutomaton clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState04GameTypeSetSelected
    @Override
    public GameType<IGameMatchX> getGameType() {
        return getBaseStateAutomaton().getGameType_();
    }

    @Override
    public void reauthorizeUser() {
        getBaseStateAutomaton().authorizeUser_(getBaseStateAutomaton().getUserName_());
    }

    @Override
    public void setGameType(GameType<IGameMatchX> gameType) {
        getBaseStateAutomaton().setGameType_(gameType);
    }
}
