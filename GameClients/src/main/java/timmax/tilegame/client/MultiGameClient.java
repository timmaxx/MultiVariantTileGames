package timmax.tilegame.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.basemodel.protocol.client.LocalClientStateAutomaton;
import timmax.tilegame.basemodel.protocol.client.jfx.FabricOfClientStatesJfx;
import timmax.tilegame.client.statuscontrol.*;
import timmax.tilegame.client.websocket.SenderOfEventOfClient;
import timmax.tilegame.transport.ISenderOfEventOfClient;

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

        ISenderOfEventOfClient senderOfEventOfClient = new SenderOfEventOfClient(localClientStateAutomatonJfx);

        root.getChildren().addAll(
                new Pane01NoConnect(senderOfEventOfClient),
                new Pane02ConnectNonIdent(senderOfEventOfClient),
                new Pane04GameTypeSetSelected(senderOfEventOfClient),
                new Pane06GameMatchSetSelected(senderOfEventOfClient),
                new Pane07GameMatchSelected(senderOfEventOfClient)
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
