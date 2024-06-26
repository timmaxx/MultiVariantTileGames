package timmax.tilegame.basemodel.protocol.client.jfx;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.transport.TransportOfClient;
import timmax.tilegame.basemodel.protocol.server_client.IFabricOfClientStateAutomaton;
import timmax.tilegame.baseview.View;
import timmax.tilegame.baseview.ViewMainField;
import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx;

import java.lang.reflect.Constructor;

public class FabricOfClientStateAutomatonJfx<ClientId> implements IFabricOfClientStateAutomaton {
    @Override
    public Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        Constructor<? extends ViewJfx<ClientId>> constructorOfViewJfx;
        try {
            Class<? extends ViewJfx<ClientId>> classOfViewJfx;
            if (classOfView.equals(ViewMainField.class)) {
                classOfViewJfx = (Class<? extends ViewJfx<ClientId>>) ViewMainFieldJfx.class;
            } else if (classOfView.equals(View.class)) {
                classOfViewJfx = (Class<? extends ViewJfx<ClientId>>) ViewJfx.class;
            } else {
                throw new RuntimeException("Unknown class");
            }
            // ToDo: В скобках перечеслены типы параметров искомого конструктора.
            //       Соответственно, если в классах, реализующих интерфейс View, изменить перечень типов параметров
            //       конструкторов, то и здесь придётся менять его.
            //       Можно было-бы в интерфейсе View определить этот перечень как константу, и возможно там-же создать
            //       default метод, который-бы проверял у реализующих классов наличие конструктора с таким-же перечнем
            //       типов параметров.
            //       Но в таком виде это не будет работать во время компиляции, да и вызов этого метода придётся делать
            //       в каждом из реализующих классов. К сожалению в интерфейсе нельзя определить сигнатуру конструктора
            //       с определённым перечнем типов параметров...
            constructorOfViewJfx = classOfViewJfx.getConstructor(TransportOfClient.class, BaseController.class, String.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return constructorOfViewJfx;
    }
}
