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
    public GameClientPaneJfx(TransportOfClient transportOfClient) {
        BaseController baseController = new BaseController(transportOfClient);

        LocalClientStateAutomaton localClientStateAutomaton = transportOfClient.getLocalClientStateAutomaton();

        Map<String, Class<? extends View>> viewName_ViewClassMap = localClientStateAutomaton.getViewName_ViewClassMap();
        for (Map.Entry<String, Class<? extends View>> viewName_ViewClassEntry : viewName_ViewClassMap.entrySet()) {
            // ToDo: Исправить
            //       Warning:(29, 21) Unchecked cast: 'java.lang.reflect.Constructor<capture<? extends timmax.tilegame.baseview.View>>' to 'java.lang.reflect.Constructor<? extends timmax.tilegame.guiengine.jfx.view.ViewJfx>'
            Constructor<? extends ViewJfx> viewConstructor =
                    new ViewJfxClass(viewName_ViewClassEntry.getValue()).getViewConstructor();
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
}
