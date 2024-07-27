package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState04GameTypeSetSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

import java.util.Set;

public class RemoteClientState04GameTypeSetSelected<ClientId> extends ClientState04GameTypeSetSelected<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState04GameTypeSetSelected(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    @Override
    public void authorizeUser(String userName, Set<GameType> gameTypeSet) {
        super.authorizeUser(userName, gameTypeSet);
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer21IdentifyAuthenticateAuthorizeUser(userName, gameTypeSet)
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
