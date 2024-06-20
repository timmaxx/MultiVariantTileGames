package timmax.tilegame.server.websocket;

import timmax.tilegame.basemodel.protocol.server_client.IFabricOfClientStateAutomaton;
import timmax.tilegame.baseview.View;

import java.lang.reflect.Constructor;

public class FabricOfRemoteClientStateAutomaton implements IFabricOfClientStateAutomaton {
    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        throw new RuntimeException("Not available for this class!");
    }
}
