package timmax.tilegame.guiengine.jfx;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.transport.TransportOfController;

import timmax.tilegame.guiengine.jfx.controller.GameSceneController;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;
import timmax.tilegame.guiengine.jfx.view.ViewJfx;

public abstract class GameClientPaneJfx extends VBox {
    public GameClientPaneJfx(Stage primaryStage, BaseModel netModel, TransportOfController transportOfController) {
        // Pane root = new VBox();
/*
        GameStackPaneController gameStackPaneController = initGameStackPaneController(transportOfController);
*/

        // ViewJfx viewMainFieldJfx = initViewOfMainField(netModel, gameStackPaneController);
        ViewJfx viewMainFieldJfx = initViewOfMainField(netModel, null);
        getChildren().add(viewMainFieldJfx);

/*
        List<Node> nodeList = initNodeList(netModel, transportOfController);
        getChildren().addAll(nodeList);

        GameSceneController gameSceneController = initGameSceneController(transportOfController);
        GameScene scene = new GameScene(this, gameSceneController);
*/
        primaryStage.setTitle(initAppTitle());
/*
        primaryStage.setScene(scene);

        netModel.createNewGame();
*/
    }
/*
    abstract protected List<Node> initNodeList(BaseModel baseModel, TransportOfController transportOfController);
*/
    abstract protected ViewJfx initViewOfMainField(BaseModel baseModel, GameStackPaneController gameStackPaneController);

    abstract protected String initAppTitle();
/*
    abstract protected GameSceneController initGameSceneController(TransportOfController transportOfController);

    abstract protected GameStackPaneController initGameStackPaneController(TransportOfController transportOfController);
*/
}