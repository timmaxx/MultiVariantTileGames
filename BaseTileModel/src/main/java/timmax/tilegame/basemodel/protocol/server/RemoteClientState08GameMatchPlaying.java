package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState08GameMatchPlaying<ClientId> extends ClientState08GameMatchPlaying<IGameMatch> {
    private final ClientId clientId;

    public RemoteClientState08GameMatchPlaying(ClientStateAutomaton<IGameMatch> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // class ClientState08GameMatchPlaying
    // ---- 7
    @Override
    public void forgetGameMatchPlaying() {
        super.forgetGameMatchPlaying();
        getClientStateAutomaton().sendEventOfServer(
                clientId,
                new EventOfServer70ForgetGameMatchPlaying()
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton getClientStateAutomaton() {
        return (RemoteClientStateAutomaton) (super.getClientStateAutomaton());
    }
}
