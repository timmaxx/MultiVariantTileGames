package timmax.tilegame.guiengine.jfx;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.baseview.View;
import timmax.tilegame.baseview.ViewClass;
import timmax.tilegame.baseview.ViewMainField;
import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx;
import timmax.tilegame.transport.ISenderOfEventOfClient;

import java.lang.reflect.Constructor;

public class ViewJfxClass extends ViewClass {
    public ViewJfxClass(Class<? extends View> viewClass) {
        super(viewClass);
    }

    @Override
    public Constructor<? extends ViewJfx> getViewConstructor() {
        Constructor<? extends ViewJfx> viewJfxConstructor;
        try {
            Class<? extends ViewJfx> classOfViewJfx;
            if (viewClass.equals(ViewMainField.class)) {
                classOfViewJfx = ViewMainFieldJfx.class;
            } else if (viewClass.equals(View.class)) {
                classOfViewJfx = ViewJfx.class;
            } else {
                throw new RuntimeException("Unknown class");
            }
            // ToDo: В скобках перечислены типы параметров искомого конструктора.
            //       Соответственно, если в классах, реализующих интерфейс View, изменить перечень типов параметров
            //       конструкторов, то и здесь придётся менять его.
            //       Можно было-бы в интерфейсе View определить этот перечень как константу, и возможно там-же создать
            //       default метод, который-бы проверял у реализующих классов наличие конструктора с таким-же перечнем
            //       типов параметров.
            //       Но в таком виде это не будет работать во время компиляции, да и вызов этого метода придётся делать
            //       в каждом из реализующих классов. К сожалению в интерфейсе нельзя определить сигнатуру конструктора
            //       с определённым перечнем типов параметров...
            // ToDo: Код сделать так, что-бы увязать:
            //       - перечень параметров конструктора ViewJfx
            //       - с перечнями типов параметров в ViewJfxClass
            //       - и с перечнем параметров в GameClientPaneJfx.
            viewJfxConstructor = classOfViewJfx.getConstructor(
                    ISenderOfEventOfClient.class, BaseController.class, String.class, GameType.class
            );
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return viewJfxConstructor;
    }
}
