package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

import java.lang.reflect.Constructor;

public class RemoteClientState02ConnectNonIdent<ClientId> extends ClientState02ConnectNonIdent<IModelOfServer, ClientId> {
    public RemoteClientState02ConnectNonIdent(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(clientStateAutomaton, transportOfServer, clientId);
    }

    // ---- 2 (Пользователь)
    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
        getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer21Login(userName));
    }

    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        throw new RuntimeException("Not available for this class!");
    }
}
