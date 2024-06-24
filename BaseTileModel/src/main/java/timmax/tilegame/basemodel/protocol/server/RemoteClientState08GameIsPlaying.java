package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState08GameIsPlaying<ClientId> extends ClientState08GameIsPlaying<IModelOfServer> {
    private final ClientId clientId;

    public RemoteClientState08GameIsPlaying(ClientStateAutomaton<IModelOfServer> clientStateAutomaton, ClientId clientId) {
        super(clientStateAutomaton);
        this.clientId = clientId;
    }

    // class ClientState08GameIsPlaying
    // ---- 7
    @Override
    public void forgetGameIsPlaying() {
        super.forgetGameIsPlaying();
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                clientId,
                new EventOfServer70GameMatchIsNotPlaying()
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}
