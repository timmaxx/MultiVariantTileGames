package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.transport.TransportOfServer;

public abstract class ClientState01NoConect<Model, ClientId> extends AbstractClientState2<Model, ClientId> implements IClientState01NoConect {
    public ClientState01NoConect(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    public ClientState01NoConect(ClientStateAutomaton<Model, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(clientStateAutomaton, transportOfServer, clientId);
    }

    @Override
    public MainGameClientStatus getMainGameClientStatus() {
        return MainGameClientStatus.NO_CONNECT;
    }
}
