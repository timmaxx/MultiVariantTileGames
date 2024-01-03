package timmax.tilegame.guiengine.jfx;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.guiengine.jfx.view.ViewJfx;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;

// ToDo: Похоже наследники этого класса становятся не нужными, т.к. клиент будет полностью универсальным.
//       А следовательно будем использовать этот класс там, где использовались его наследники.
public abstract class GameClientPaneJfx extends VBox {
    public GameClientPaneJfx(Stage primaryStage, BaseModel netModel, TransportOfClient transportOfClient) {
        // Инициализация контроллера управления мышью (работает только над главной выборкой):
        GameStackPaneController gameStackPaneController = initGameStackPaneController(transportOfClient);

        ViewJfx viewMainFieldJfx = initViewOfMainField(netModel, gameStackPaneController);
        getChildren().add(viewMainFieldJfx);

/*
        List<Node> nodeList = initNodeList(netModel, transportOfController);
        getChildren().addAll(nodeList);
*/
        // Инициализация контроллера управления клавиатурой (работает над всей сценой):
        // GameSceneController gameSceneController = initGameSceneController(transportOfClient);

        // GameScene scene = new GameScene(this, gameSceneController);

        primaryStage.setTitle(initAppTitle());
    }

    /*
        abstract protected List<Node> initNodeList(BaseModel baseModel, TransportOfController transportOfController);
    */
    abstract protected ViewJfx initViewOfMainField(BaseModel baseModel, GameStackPaneController gameStackPaneController);

    abstract protected String initAppTitle();

    // abstract protected GameSceneController initGameSceneController(TransportOfClient transportOfClient);

    abstract protected GameStackPaneController initGameStackPaneController(TransportOfClient transportOfClient);
}
