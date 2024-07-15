package timmax.tilegame.guiengine.jfx;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javafx.scene.layout.VBox;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.baseview.View;
import timmax.tilegame.baseview.ViewMainField;
import timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.guiengine.jfx.view.ViewJfx;

public class GameClientPaneJfx extends VBox {
    public GameClientPaneJfx(TransportOfClient transportOfClient) {
        BaseController baseController = new BaseController(transportOfClient);

        LocalClientStateAutomaton localClientStateAutomaton = transportOfClient.getLocalClientStateAutomaton();

        Map<String, Class<? extends View>> viewName_ViewClassMap = localClientStateAutomaton.getViewName_ViewClassMap();
        for (Map.Entry<String, Class<? extends View>> viewName_ViewClassEntry : viewName_ViewClassMap.entrySet()) {
            // ToDo: Исправить
            //       Warning:(29, 21) Unchecked cast: 'java.lang.reflect.Constructor<capture<? extends timmax.tilegame.baseview.View>>' to 'java.lang.reflect.Constructor<? extends timmax.tilegame.guiengine.jfx.view.ViewJfx>'
            Constructor<? extends ViewJfx> viewConstructor =
                    (Constructor<? extends ViewJfx>)
                            getViewConstructor(viewName_ViewClassEntry.getValue())
                    ;
            ViewJfx viewJfx;
            try {
                viewJfx = viewConstructor.newInstance(transportOfClient, baseController, viewName_ViewClassEntry.getKey());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            localClientStateAutomaton.addView(viewJfx);
            getChildren().add(viewJfx);
        }
    }

    // Что-то похожее на фабричный метод - т.е.:
    // - на вход подаём базовый класс выборки,
    // - по этому классу определяем класс выборки, реализованной в JFX,
    // - для этой выборки определяем конструктор.
    // ToDo: Переименовать параметр classOfView
    private static Constructor<? extends View> getViewConstructor(Class<? extends View> classOfView) {
        // ToDo: Переименовать.
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
            // ToDo: В скобках перечислены типы параметров искомого конструктора.
            //       Соответственно, если в классах, реализующих интерфейс View, изменить перечень типов параметров
            //       конструкторов, то и здесь придётся менять его.
            //       Можно было-бы в интерфейсе View определить этот перечень как константу, и возможно там-же создать
            //       default метод, который-бы проверял у реализующих классов наличие конструктора с таким-же перечнем
            //       типов параметров.
            //       Но в таком виде это не будет работать во время компиляции, да и вызов этого метода придётся делать
            //       в каждом из реализующих классов. К сожалению в интерфейсе нельзя определить сигнатуру конструктора
            //       с определённым перечнем типов параметров...
            constructorOfViewJfx = classOfViewJfx.getConstructor(
                    TransportOfClient.class, BaseController.class, String.class
            );
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return constructorOfViewJfx;
    }
}
