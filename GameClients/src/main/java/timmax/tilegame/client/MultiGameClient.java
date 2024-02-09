package timmax.tilegame.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.client.statuscontrol.*;
import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class MultiGameClient extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new VBox();

        MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse = new MultiGameWebSocketClientManyTimesUse();

        Pane01ServerConnect pane01ServerConnect = new Pane01ServerConnect(multiGameWebSocketClientManyTimesUse);
        Pane02UserLogin pane02UserLogin = new Pane02UserLogin(multiGameWebSocketClientManyTimesUse);
        Pane03GetGameTypeSet pane03GetGameTypeSet = new Pane03GetGameTypeSet(multiGameWebSocketClientManyTimesUse);
        Pane04SelectGameType pane04SelectGameType = new Pane04SelectGameType(multiGameWebSocketClientManyTimesUse);
        Pane05GetGameMatchSet pane05GetGameMatchSet = new Pane05GetGameMatchSet(multiGameWebSocketClientManyTimesUse);
        Pane06SelectGameMatch pane06SelectGameMatch = new Pane06SelectGameMatch(multiGameWebSocketClientManyTimesUse);
        Pane07GameMatchPlaying pane07GameMatchPlaying = new Pane07GameMatchPlaying(multiGameWebSocketClientManyTimesUse);

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
        primaryStage.setTitle("Multi Game Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.exit(0);
    }
}
