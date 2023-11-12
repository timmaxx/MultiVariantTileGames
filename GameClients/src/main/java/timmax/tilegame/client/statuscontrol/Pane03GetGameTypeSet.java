package timmax.tilegame.client.statuscontrol;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.websocket.client.MultiGameWebSocketClient;
import timmax.tilegame.websocket.client.Observer020OnLogout;
import timmax.tilegame.websocket.client.Observer021OnLogin;

public class Pane03GetGameTypeSet extends HBox implements Observer021OnLogin, Observer020OnLogout {
    private MultiGameWebSocketClient netModel;
    private final Button buttonGetGameTypeSet;


    public Pane03GetGameTypeSet() {
        buttonGetGameTypeSet = new Button("Get the game type set");

        getChildren().addAll(buttonGetGameTypeSet);

        buttonGetGameTypeSet.setOnAction(event -> {
            // System.out.println("buttonLogin.action. event = " + event);
            netModel.getGameTypeSet();
        });
    }

    @Override
    public void updateOnLogout() {
    }

    @Override
    public void updateOnLogin(ResultOfCredential resultOfCredential, MultiGameWebSocketClient multiGameWebSocketClient) {
        this.netModel = multiGameWebSocketClient;
    }
}