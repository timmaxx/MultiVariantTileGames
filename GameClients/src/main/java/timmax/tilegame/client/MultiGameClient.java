package timmax.tilegame.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.basemodel.protocol.client.ModelOfClient;
import timmax.tilegame.basemodel.protocol.client.jfx.LocalClientStateJfx;
import timmax.tilegame.client.statuscontrol.*;
import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;
import timmax.tilegame.transport.TransportOfClient;

public class MultiGameClient extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new VBox();

        TransportOfClient transportOfClient = new MultiGameWebSocketClientManyTimesUse();
        LocalClientState localClientStateJfx = new LocalClientStateJfx();
        IModelOfClient iModelOfClient = new ModelOfClient(transportOfClient, localClientStateJfx);
        // ToDo: Как-то не нравится мне, что модель приходится инициализировать через сеттер.
        transportOfClient.setModelOfClient(iModelOfClient);

        Pane01ServerConnect pane01ServerConnect = new Pane01ServerConnect(iModelOfClient, transportOfClient);
        Pane02UserLogin pane02UserLogin = new Pane02UserLogin(iModelOfClient, transportOfClient);
        Pane03GetGameTypeSet pane03GetGameTypeSet = new Pane03GetGameTypeSet(iModelOfClient, transportOfClient);
        Pane04SelectGameType pane04SelectGameType = new Pane04SelectGameType(iModelOfClient, transportOfClient);
        Pane05GetGameMatchSet pane05GetGameMatchSet = new Pane05GetGameMatchSet(iModelOfClient, transportOfClient);
        Pane06SelectGameMatch pane06SelectGameMatch = new Pane06SelectGameMatch(iModelOfClient, transportOfClient);
        Pane07GameMatchPlaying pane07GameMatchPlaying = new Pane07GameMatchPlaying(iModelOfClient, transportOfClient);

        root.getChildren().addAll(
                pane01ServerConnect,
                pane02UserLogin,
                pane03GetGameTypeSet,
                pane04SelectGameType,
                pane05GetGameMatchSet,
                pane06SelectGameMatch,
                pane07GameMatchPlaying
        );

        Scene scene = new Scene(root);
        primaryStage.setTitle("Multi tile game client");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}
