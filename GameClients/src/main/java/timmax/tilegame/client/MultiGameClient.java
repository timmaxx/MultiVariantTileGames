package timmax.tilegame.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.client.jfx.FabricOfClientStatesJfx;
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

        LocalClientStateAutomaton localClientStateAutomatonJfx = new LocalClientStateAutomaton(
                new FabricOfClientStatesJfx()
        );

        TransportOfClient transportOfClient = new MultiGameWebSocketClientManyTimesUse(localClientStateAutomatonJfx);

        root.getChildren().addAll(
                new Pane01NoConnect(transportOfClient),
                new Pane02ConnectNonIdent(transportOfClient),
                new Pane04GameTypeSetSelected(transportOfClient),
                new Pane06SelectGameMatch(transportOfClient),
                new Pane07GameMatchPlaying(transportOfClient)
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
