package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientState05GameTypeSelected;
import timmax.tilegame.basemodel.protocol.server_client.ClientStateAutomaton;
import timmax.tilegame.baseview.View;
import timmax.tilegame.baseview.ViewMainField;
import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx;

import java.lang.reflect.Constructor;

public class LocalClientState05GameTypeSelectedJfx<Model, ClientId> extends LocalClientState05GameTypeSelected<Model, ClientId> {
    public LocalClientState05GameTypeSelectedJfx(ClientStateAutomaton<Model, ClientId> clientStateAutomaton) {
        super(clientStateAutomaton);
    }

    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        Constructor<? extends ViewJfx> constructorOfViewJfx;
        try {
            Class<? extends ViewJfx> classOfViewJfx;
            if (classOfView.equals(ViewMainField.class)) {
                classOfViewJfx = ViewMainFieldJfx.class;
            } else if (classOfView.equals(View.class)) {
                classOfViewJfx = ViewJfx.class;
            } else {
                throw new RuntimeException("Unknown class");
            }
            // ToDo: В скобках перечеслены типы параметров искомого конструктора.
            //       Соответственно, если в классах, реализующих интерфейс View, изменить перечень типов параметров
            //       конструкторов, то и здесь придётся менять его.
            //       Можно было-бы в интерфейсе View определить этот перечень как константу и возможно там-же создать
            //       default метод, который-бы проверял у реализующих классов наличие конструктора с таким-же перечнем
            //       типов параметров.
            //       Но в таком виде это не будет работать во время компиляции, да и вызов этого метода придётся делать
            //       в каждом из реализующих классов. К сожалению в интерфейсе нельзя определить сигнатуру конструктора
            //       с определённым перечнем типов параметров...
            constructorOfViewJfx = classOfViewJfx.getConstructor(IModelOfClient.class, BaseController.class, String.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return constructorOfViewJfx;
    }
}
