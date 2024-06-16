package timmax.tilegame.basemodel.protocol.server_client;

import timmax.tilegame.basemodel.clientappstatus.MainGameClientStatus;
import timmax.tilegame.baseview.View;

import java.lang.reflect.Constructor;

// ToDo: Интерфейс удалить после упразднения его методов.
public interface IClientState00 {
    // Метод возможно и не нужен.
    MainGameClientStatus getMainGameClientStatus();
    // Метод следует убрать из этого интерфейса, т.к. он будет реализован в ClientStateAutomaton
    Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView);
}
