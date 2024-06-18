package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;
import timmax.tilegame.baseview.View;

import java.lang.reflect.Constructor;

public class RemoteClientState02ConnectNonIdent<ClientId> extends ClientState02ConnectNonIdent<IModelOfServer, ClientId> {
    public RemoteClientState02ConnectNonIdent(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // ---- 2 (Пользователь)
    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(getClientStateAutomaton().getClientId(), new EventOfServer21Login(userName));
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
