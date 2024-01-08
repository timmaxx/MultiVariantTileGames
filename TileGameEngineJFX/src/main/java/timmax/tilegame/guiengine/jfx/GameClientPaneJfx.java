package timmax.tilegame.guiengine.jfx;

import javafx.scene.layout.VBox;

import timmax.tilegame.basecontroller.BaseController;
import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx;

// ToDo: А нужен-ли этот класс?
public class GameClientPaneJfx extends VBox {
    public GameClientPaneJfx(BaseModel netModel, TransportOfClient transportOfClient) {
        BaseController baseController = new BaseController(transportOfClient);

        ViewJfx viewMainFieldJfx = new ViewMainFieldJfx(netModel, baseController);
        getChildren().add(viewMainFieldJfx);
    }
}
