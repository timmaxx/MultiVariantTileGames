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
        Map<String, Class< ? extends View>> mapOfVieName_ViewClass = localClientState.getMapOfVieName_ViewClass();
        Map<String, View>  mapOfVieName_View = localClientState.getMapOfVieName_View();

        for (Map.Entry<String, Class< ? extends View>> entry: mapOfVieName_ViewClass.entrySet()) {
            Constructor<? extends ViewJfx> viewConstructor = (Constructor<? extends ViewJfx>) localClientState.getViewConstructor(
                    entry.getValue(),
                    baseController,
                    entry.getKey()
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
