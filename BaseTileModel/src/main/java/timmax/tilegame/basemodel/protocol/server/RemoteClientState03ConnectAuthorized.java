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
    // ---- 2 (Пользователь)
    @Override
    public void forgetUserName() {
        super.forgetUserName();
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(clientId, new EventOfServer20Logout());
    }

    // ---- 3
    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(clientId, new EventOfServer31GetGameTypeSet(setOfModelOfServerDescriptor));
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>)(super.getClientStateAutomaton());
    }
}
