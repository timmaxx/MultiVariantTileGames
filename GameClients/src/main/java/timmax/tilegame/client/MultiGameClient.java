package timmax.tilegame.client;

import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import timmax.tilegame.client.statuscontrol.Pane01ServerConnect;
import timmax.tilegame.client.statuscontrol.Pane02UserLogin;
import timmax.tilegame.client.statuscontrol.Pane03GetGameTypeSet;


public class MultiGameClient extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new VBox();

        Pane01ServerConnect pane01ServerConnect = new Pane01ServerConnect();
        Pane02UserLogin pane02UserLogin = new Pane02UserLogin();
        Pane03GetGameTypeSet pane03GetGameTypeSet = new Pane03GetGameTypeSet();

/*
        Map<Observer011OnOpen, String> mapOfObserver011OnOpen__String = new HashMap<>();
        mapOfObserver011OnOpen__String.put(pane02UserLogin, "");
        pane01ServerConnect.setMapOfObserver011OnOpen__String(mapOfObserver011OnOpen__String);
*/
        pane01ServerConnect.setMapOfObserver011OnOpen__String(new HashMap<>() {{
            put(pane02UserLogin, "");
        }});

        pane01ServerConnect.setMapOfObserver021OnLogin__String(new HashMap<>() {{
            put(pane03GetGameTypeSet, "");
        }});

        // LabelStatusBar // Найти или создать контрол, который обычно размещается внизу окна
        // и может показывать инфу о статусах приложения.

        root.getChildren().addAll(pane01ServerConnect, pane02UserLogin, pane03GetGameTypeSet);

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