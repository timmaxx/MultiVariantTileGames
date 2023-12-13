package timmax.tilegame.guiengine.jfx;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.transport.TransportOfClient;

import timmax.tilegame.guiengine.jfx.controller.GameSceneController;
import timmax.tilegame.guiengine.jfx.controller.GameStackPaneController;
import timmax.tilegame.guiengine.jfx.view.ViewJfx;

import timmax.tilegame.websocket.client.NetModel;
import timmax.tilegame.websocket.client.TransportOfClientWebSocket;

public abstract class GameClientWebSocketJfx extends Application {
    @Override
    public void start(Stage primaryStage) throws URISyntaxException {
        // Для модели отделенной от клиента где-то здесь открывать клиентский WebSocket.
        // И создавать свой BaseModel (но уже не тот, что на сервере, а тот, который будет соединением к серверному).
        NetModel netModel;
        try {
            netModel = new NetModel(new URI("ws://localhost:8887"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Pane root = new VBox();

        TransportOfClient transportOfClient = new TransportOfClientWebSocket(netModel);

        GameStackPaneController gameStackPaneController = initGameStackPaneController(transportOfClient);

        ViewJfx viewMainFieldJfx = initViewOfMainField(netModel, gameStackPaneController);
        root.getChildren().add(viewMainFieldJfx);

        List<Node> nodeList = initNodeList(netModel, transportOfClient);
        root.getChildren().addAll(nodeList);

        GameSceneController gameSceneController = initGameSceneController(transportOfClient);
        GameScene scene = new GameScene(root, gameSceneController);

        primaryStage.setTitle(initAppTitle());
        // primaryStage.setResizable( false);
        primaryStage.setScene(scene);

        netModel.createNewGame();

        primaryStage.show();
    }

    abstract protected List<Node> initNodeList(BaseModel baseModel, TransportOfClient transportOfClient);

    abstract protected ViewJfx initViewOfMainField(BaseModel baseModel, GameStackPaneController gameStackPaneController);

    abstract protected String initAppTitle();

    abstract protected GameSceneController initGameSceneController(TransportOfClient transportOfClient);

    abstract protected GameStackPaneController initGameStackPaneController(TransportOfClient transportOfClient);
}