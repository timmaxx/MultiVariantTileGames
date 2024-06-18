package timmax.tilegame.basemodel.protocol.server;

import timmax.tilegame.basemodel.protocol.server_client.ClientState01NoConect;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.baseview.View;

import java.lang.reflect.Constructor;

public class RemoteClientState01NoConect<ClientId> extends ClientState01NoConect<IModelOfServer, ClientId> {
    public RemoteClientState01NoConect(ClientStateAutomaton<IModelOfServer, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        throw new RuntimeException("Not available for this class!");
    }
}
