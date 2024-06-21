package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;

public class RemoteClientState08GameIsPlaying<ClientId> extends ClientState08GameIsPlaying<IModelOfServer> {
    public RemoteClientState08GameIsPlaying(ClientStateAutomaton<IModelOfServer> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // class AbstractClientState
    // ---- 7
    @Override
    public void forgetGameIsPlaying() {
        super.forgetGameIsPlaying();
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer70GameMatchIsNotPlaying()
        );
    }

    // class AbstractClientState
    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}
