package timmax.tilegame.guiengine.jfx;

import javafx.scene.layout.VBox;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;
import timmax.tilegame.guiengine.jfx.controller.GameSceneController;

// ToDo: Поскольку отказался от использования наследников для этого класса, то и три метода тоже не нужны.
//       Да и вообще, а нужен-ли этот класс?
public class GameClientPaneJfx extends VBox {
    // ToDo: Stage primaryStage удалить параметр.
    public GameClientPaneJfx(BaseModel netModel, TransportOfClient transportOfClient) {
        // ToDo: 1. Оба контроллера объединить в один и/или
        //       2. Нужны-ли классы контроллеры и в т.ч. с такими именами?

        // Инициализация контроллера управления мышью (работает только над главной выборкой):
        GameStackPaneController gameStackPaneController = new GameStackPaneController(transportOfClient);

        // Инициализация контроллера управления клавиатурой (работает над всей сценой):
        GameSceneController gameSceneController = new GameSceneController(transportOfClient);

        ViewJfx viewMainFieldJfx = new ViewMainFieldJfx(netModel, gameStackPaneController, gameSceneController);
        getChildren().add(viewMainFieldJfx);
    }
}
