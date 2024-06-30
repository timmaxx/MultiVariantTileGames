package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState03ConnectAuthorized;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

import java.util.Set;

public class RemoteClientState03ConnectAuthorized<ClientId> extends ClientState03ConnectAuthorized<IModelOfServer> {
    private final ClientId clientId;

    public RemoteClientState03ConnectAuthorized(ClientStateAutomaton<IModelOfServer> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // class ClientState03ConnectAuthorized
    // ---- 2
    @Override
    public void forgetUser() {
        super.forgetUser();
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(clientId, new EventOfServer20ForgetUser());
    }

    // ---- 3
    @Override
    public void setGameTypeSet(Set<GameType> gameTypeSet) {
        super.setGameTypeSet(gameTypeSet);
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(clientId, new EventOfServer31SetGameTypeSet(gameTypeSet));
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>)(super.getClientStateAutomaton());
    }
}
