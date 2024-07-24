package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

import java.util.Set;

public class RemoteClientState02ConnectNonIdent<ClientId> extends ClientState02ConnectNonIdent<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState02ConnectNonIdent(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // class ClientState02ConnectNonIdent
    // ---- 2 (Пользователь)
    @Override
    public void authorizeUser(String userName, Set<GameType> gameTypeSet) {
        super.authorizeUser(userName, gameTypeSet);
        getClientStateAutomaton().sendEventOfServer(clientId, new EventOfServer21IdentifyAuthenticateAuthorizeUser(userName, gameTypeSet));
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton)(super.getClientStateAutomaton());
    }
}
