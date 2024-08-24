package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState02ConnectNonIdent<ClientId> extends ClientState02ConnectNonIdent<IGameMatch> {
    public RemoteClientState02ConnectNonIdent(ClientStateAutomaton<IGameMatch> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public void connect() {
        super.connect();
        getClientStateAutomaton().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer11OpenConnectWithoutUserIdentify()
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>)(super.getClientStateAutomaton());
    }
}
