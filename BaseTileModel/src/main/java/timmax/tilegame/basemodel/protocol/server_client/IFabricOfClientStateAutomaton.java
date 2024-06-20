package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.baseview.View;

import java.lang.reflect.Constructor;

public interface IFabricOfClientStateAutomaton {
    Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView);
}
