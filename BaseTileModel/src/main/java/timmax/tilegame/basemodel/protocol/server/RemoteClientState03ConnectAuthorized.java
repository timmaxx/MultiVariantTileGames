package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.ClientState03ConnectAuthorized;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

import java.lang.reflect.Constructor;
import java.util.Set;

public class RemoteClientState03ConnectAuthorized<ClientId> extends ClientState03ConnectAuthorized<IModelOfServer, ClientId> {
    public RemoteClientState03ConnectAuthorized(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(clientStateAutomaton, transportOfServer, clientId);
    }

    // ---- 2 (Пользователь)
    @Override
    public void forgetUserName() {
        super.forgetUserName();
        getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer20Logout());
    }

    // ---- 3
    @Override
    public void setGameTypeSet(Set<ModelOfServerDescriptor> setOfModelOfServerDescriptor) {
        super.setGameTypeSet(setOfModelOfServerDescriptor);
        getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer31GetGameTypeSet(setOfModelOfServerDescriptor));
    }

    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        throw new RuntimeException("Not available for this class!");
    }
}
