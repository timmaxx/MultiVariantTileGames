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

// ToDo: А нужен-ли этот класс?
public class GameClientPaneJfx extends VBox {
    public GameClientPaneJfx(TransportOfClient transportOfClient) {
        BaseController baseController = new BaseController(transportOfClient);

        LocalClientStateAutomaton localClientStateAutomaton = transportOfClient.getLocalClientStateAutomaton();
        // ToDo: Переименовать.
        Map<String, Class<? extends View>> mapOfViewName_ViewClass = localClientStateAutomaton.getViewName_ViewClassMap();
        // ToDo: В методе идёт цикл по элементам мапы mapOfVieName_View (и вставка в мапу mapOfViewName_ViewClass),
        //       а значит можно перенести код в класс мапы mapOfVieName_View (но пока этот класс не выделен в отдельную мапу...).
        // ToDo: Переименовать.
        Map<String, View> mapOfVieName_View = localClientStateAutomaton.getViewName_ViewMap();

        for (Map.Entry<String, Class<? extends View>> entry : mapOfViewName_ViewClass.entrySet()) {
            // ToDo: Исправить
            //       Warning:(37, 21) Unchecked cast: 'java.lang.reflect.Constructor<capture<? extends timmax.tilegame.baseview.View>>' to 'java.lang.reflect.Constructor<? extends timmax.tilegame.guiengine.jfx.view.ViewJfx>'
            Constructor<? extends ViewJfx> viewConstructor =
                    (Constructor<? extends ViewJfx>) getViewConstructor(
                            entry.getValue()
                    );
            ViewJfx viewJfx;
            try {
                viewJfx = viewConstructor.newInstance(transportOfClient, baseController, entry.getKey());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            mapOfVieName_View.put(entry.getKey(), viewJfx);
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
