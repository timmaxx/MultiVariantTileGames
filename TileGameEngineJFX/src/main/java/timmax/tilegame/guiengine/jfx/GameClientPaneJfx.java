package timmax.tilegame.guiengine.jfx;

import javafx.scene.layout.VBox;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx;

// ToDo: А нужен-ли этот класс?
public class GameClientPaneJfx extends VBox {
    public GameClientPaneJfx(IModelOfClient iModelOfClient, TransportOfClient transportOfClient, String viewName) {
        BaseController baseController = new BaseController(transportOfClient);

        ViewJfx viewMainFieldJfx = new ViewMainFieldJfx(iModelOfClient, baseController, viewName);
        getChildren().add(viewMainFieldJfx);
    }
}
