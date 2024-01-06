package timmax.tilegame.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;
import timmax.tilegame.client.statuscontrol.Pane01ServerConnect;
import timmax.tilegame.client.statuscontrol.Pane02UserLogin;
import timmax.tilegame.client.statuscontrol.Pane03GetGameTypeSet;
import timmax.tilegame.client.statuscontrol.Pane04SelectGameType;
import timmax.tilegame.client.statuscontrol.Pane09Gaming;

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
        Pane09Gaming pane09Gaming = new Pane09Gaming(multiGameWebSocketClientManyTimesUse);

        root.getChildren().addAll(pane01ServerConnect, pane02UserLogin, pane03GetGameTypeSet, pane04SelectGameType, pane09Gaming);

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
