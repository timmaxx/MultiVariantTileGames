package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState03ConnectAuthorized;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.baseview.View;

import java.lang.reflect.Constructor;
import java.util.Set;

public class RemoteClientState03ConnectAuthorized<ClientId> extends ClientState03ConnectAuthorized<IModelOfServer, ClientId> {
    public RemoteClientState03ConnectAuthorized(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

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

    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        throw new RuntimeException("Not available for this class!");
    }

    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>)(super.getClientStateAutomaton());
    }
}
