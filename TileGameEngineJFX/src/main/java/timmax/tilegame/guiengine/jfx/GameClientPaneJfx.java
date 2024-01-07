package timmax.tilegame.guiengine.jfx;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;
// import timmax.tilegame.guiengine.jfx.controller.GameSceneController;

// ToDo: Поскольку отказался от использования наследников для этого класса, то и три метода тоже не нужны.
//       Да и вообще, а нужен-ли этот класс?
public class GameClientPaneJfx extends VBox {
    private LocalKeyEventHandler localKeyEventHandler;

    // ToDo: Stage primaryStage удалить параметр.
    public GameClientPaneJfx(Stage primaryStage, BaseModel netModel, TransportOfClient transportOfClient) {
        localKeyEventHandler = new LocalKeyEventHandler();

        // Инициализация контроллера управления мышью (работает только над главной выборкой):
        GameStackPaneController gameStackPaneController = new GameStackPaneController(transportOfClient);

        ViewJfx viewMainFieldJfx = new ViewMainFieldJfx(netModel, gameStackPaneController);
        getChildren().add(viewMainFieldJfx);

        // Инициализация контроллера управления клавиатурой (работает над всей сценой):
        // GameSceneController gameSceneController = new GameSceneController(transportOfClient);

        // primaryStage.getScene().setOnKeyPressed(localKeyEventHandler);
        setOnKeyPressed(localKeyEventHandler);

        setFocusTraversable(true);
    }

    private static class LocalKeyEventHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            System.out.println("GameClientPaneJfx. class LocalKeyEventHandler. method handle");
            System.out.println("  event.getCode() = " + event.getCode());
        }
    }

}
