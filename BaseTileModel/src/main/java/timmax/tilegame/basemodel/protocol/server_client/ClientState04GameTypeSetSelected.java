package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.protocol.server.GameType;

public abstract class ClientState04GameTypeSetSelected<GameMatchX extends IGameMatchX> extends ClientState02ConnectNonIdent<GameMatchX> {
    public ClientState04GameTypeSetSelected(ClientStateAutomaton<GameMatchX> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // interface IClientState04GameTypeSetSelected
    @Override
    public void reauthorizeUser() {
        String userName = getClientStateAutomaton().getUserName_();
        authorizeUser(userName);
    }

    @Override
    public void selectGameType(GameType gameType) {
        getClientStateAutomaton().selectGameType_(gameType);
    }
}
