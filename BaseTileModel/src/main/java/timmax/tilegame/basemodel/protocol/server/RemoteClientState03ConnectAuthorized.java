package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState03ConnectAuthorized;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;

import java.util.Set;

public class RemoteClientState03ConnectAuthorized<ClientId> extends ClientState03ConnectAuthorized<IModelOfServer> {
    public RemoteClientState03ConnectAuthorized(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 2 (Пользователь)
    @Override
    public void forgetUserName() {
        super.forgetUserName();
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(getClientStateAutomaton().getClientId(), new EventOfServer20Logout());
    }

    // ---- 3
    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(getClientStateAutomaton().getClientId(), new EventOfServer31GetGameTypeSet(setOfModelOfServerDescriptor));
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>)(super.getClientStateAutomaton());
    }
}
