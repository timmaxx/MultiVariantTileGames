package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState02ConnectNonIdent<ClientId> extends ClientState02ConnectNonIdent<IModelOfServer> {
    public RemoteClientState02ConnectNonIdent(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class ClientState02ConnectNonIdent
    // ---- 2 (Пользователь)
    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(getClientStateAutomaton().getClientId(), new EventOfServer21Login(userName));
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>)(super.getClientStateAutomaton());
    }
}
