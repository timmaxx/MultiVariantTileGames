package timmax.tilegame.guiengine.jfx;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javafx.scene.layout.VBox;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.baseview.View;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.guiengine.jfx.view.ViewJfx;

// ToDo: А нужен-ли этот класс?
public class GameClientPaneJfx extends VBox {
    public GameClientPaneJfx(
            IModelOfClient iModelOfClient,
            TransportOfClient transportOfClient) {

        BaseController baseController = new BaseController(transportOfClient);

        LocalClientState localClientState = transportOfClient.getLocalClientState();
        Map<String, Class< ? extends View>> mapOfViewName_ViewClass = localClientState.getGameType().getMapOfViewNameViewClass();
        Map<String, View>  mapOfVieName_View = localClientState.getMapOfViewName_View();

        for (Map.Entry<String, Class< ? extends View>> entry: mapOfViewName_ViewClass.entrySet()) {
            // ToDo: Исправить
            //       Warning:(32, 62) Unchecked cast: 'java.lang.reflect.Constructor<capture<? extends timmax.tilegame.baseview.View>>' to 'java.lang.reflect.Constructor<? extends timmax.tilegame.guiengine.jfx.view.ViewJfx>'
            Constructor<? extends ViewJfx> viewConstructor = (Constructor<? extends ViewJfx>) localClientState.getViewConstructor(
                    entry.getValue()
            );
            ViewJfx viewJfx;
            try {
                viewJfx = viewConstructor.newInstance(iModelOfClient, baseController, entry.getKey());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            mapOfVieName_View.put(entry.getKey(), viewJfx);
            getChildren().add(viewJfx);
        }
    }
}
