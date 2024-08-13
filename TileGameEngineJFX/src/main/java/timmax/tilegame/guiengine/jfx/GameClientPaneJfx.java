package timmax.tilegame.guiengine.jfx;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javafx.scene.layout.VBox;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.guiengine.jfx.view.ViewJfx;

public class GameClientPaneJfx extends VBox {
    public void createViews(TransportOfClient transportOfClient) {
        BaseController baseController = new BaseController(transportOfClient);
        LocalClientStateAutomaton localClientStateAutomaton = transportOfClient.getLocalClientStateAutomaton();

        // ToDo: Переделать. См. комментарий к
        //       LocalClientStateAutomaton :: Map<String, Class<? extends View>> getViewName_ViewClassMap()
        Map<String, Class<? extends View>> viewName_ViewClassMap = localClientStateAutomaton.getViewName_ViewClassMap();

        for (Map.Entry<String, Class<? extends View>> viewName_ViewClassEntry : viewName_ViewClassMap.entrySet()) {
            // ToDo: Исправить
            //       Warning:(29, 21) Unchecked cast: 'java.lang.reflect.Constructor<capture<? extends timmax.tilegame.baseview.View>>' to 'java.lang.reflect.Constructor<? extends timmax.tilegame.guiengine.jfx.view.ViewJfx>'
            Constructor<? extends ViewJfx> viewConstructor =
                    new ViewJfxClass(viewName_ViewClassEntry.getValue()).getViewConstructor();
            ViewJfx viewJfx;
            try {
                // ToDo: Код сделать так, что-бы увязать:
                //       - перечень параметров конструктора ViewJfx
                //       - с перечнями типов параметров в ViewJfxClass
                //       - и с перечнем параметров в GameClientPaneJfx.
                viewJfx = viewConstructor.newInstance(transportOfClient, baseController, viewName_ViewClassEntry.getKey(), localClientStateAutomaton.getGameType());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            localClientStateAutomaton.addView(viewJfx);
            getChildren().add(viewJfx);
        }
    }

    public void clearChildren() {
        getChildren().clear();
    }
}
