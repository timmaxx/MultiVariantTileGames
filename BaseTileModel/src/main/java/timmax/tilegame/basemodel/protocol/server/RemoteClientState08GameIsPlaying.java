package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.*;
import timmax.tilegame.basemodel.protocol.server_client.*;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfServer;

import java.lang.reflect.Constructor;

public class RemoteClientState08GameIsPlaying<ClientId> extends ClientState08GameIsPlaying<IModelOfServer, ClientId> {
    public RemoteClientState08GameIsPlaying(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton, TransportOfServer<ClientId> transportOfServer, ClientId clientId) {
        super(clientStateAutomaton, transportOfServer, clientId);
    }

    // ---- 7
    @Override
    public void forgetGameIsPlaying() {
        super.forgetGameIsPlaying();
        getTransportOfServer().sendEventOfServer(getClientId(), new EventOfServer70GameMatchIsNotPlaying());
    }

    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        throw new RuntimeException("Not available for this class!");
    }
}
