package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;
import timmax.tilegame.baseview.View;

import java.lang.reflect.Constructor;

public class RemoteClientState08GameIsPlaying<ClientId> extends ClientState08GameIsPlaying<IModelOfServer, ClientId> {
    public RemoteClientState08GameIsPlaying(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    // ---- 7
    @Override
    public void forgetGameIsPlaying() {
        super.forgetGameIsPlaying();
        getClientStateAutomaton().getTransportOfServer().sendEventOfServer(
                getClientStateAutomaton().getClientId(),
                new EventOfServer70GameMatchIsNotPlaying()
        );
    }

    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        throw new RuntimeException("Not available for this class!");
    }

    @Override
    public RemoteClientStateAutomaton<ClientId> getClientStateAutomaton() {
        return (RemoteClientStateAutomaton<ClientId>) (super.getClientStateAutomaton());
    }
}
