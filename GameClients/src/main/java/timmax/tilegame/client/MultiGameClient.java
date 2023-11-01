package timmax.tilegame.client;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.client.statuscontrol.Pane01ServerConnect;
import timmax.tilegame.client.statuscontrol.Pane02UserLogin;
import timmax.tilegame.websocket.client.Observer011OnOpen;

public class MultiGameClient extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new VBox();

        Pane01ServerConnect pane01ServerConnect = new Pane01ServerConnect();
        Pane02UserLogin pane02UserLogin = new Pane02UserLogin();

        Map<Observer011OnOpen, String> mapOfObserver011OnOpen__String = new HashMap<>();
        mapOfObserver011OnOpen__String.put(pane02UserLogin, "");
        pane01ServerConnect.setMapOfObserver011OnOpen__String(mapOfObserver011OnOpen__String);

        // LabelStatusBar // Найти или создать контрол, который обычно размещается внизу окна
        // и может показывать инфу о статусах приложения.

        root.getChildren().addAll(pane01ServerConnect, pane02UserLogin);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Multi Game Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}