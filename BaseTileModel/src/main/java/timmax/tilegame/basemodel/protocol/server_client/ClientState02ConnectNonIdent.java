package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.transport.TransportOfServer;

public abstract class ClientState02ConnectNonIdent<Model, ClientId> extends AbstractClientState2<Model, ClientId> implements IClientState02ConnectNonIdent {
    public ClientState02ConnectNonIdent(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    public ClientState02ConnectNonIdent(ClientStateAutomaton<Model, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(clientStateAutomaton, transportOfServer, clientId);
    }

    @Override
    public void setUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new NullPointerException("UserName is null. It must be not null for this method.");
        }
        getClientStateAutomaton().clientState03ConnectAuthorized.setUserName_(userName);
    }

    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.CONNECT_NON_IDENT;
    }
}
