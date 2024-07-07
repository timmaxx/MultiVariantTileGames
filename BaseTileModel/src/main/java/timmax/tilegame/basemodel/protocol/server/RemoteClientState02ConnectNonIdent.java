package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState02ConnectNonIdent<ClientId> extends ClientState02ConnectNonIdent<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState02ConnectNonIdent(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // class ClientState02ConnectNonIdent
    // ---- 2 (Пользователь)
    @Override
    public void setUser(String userName) {
        super.setUser(userName);
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(clientId, new EventOfServer21SetUser(userName));
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>)(super.getClientStateAutomaton());
    }
}
