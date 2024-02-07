package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Button;
import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane05GetGamePlaySet extends AbstractConnectStatePane {
    public Pane05GetGamePlaySet(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        // Контролы для продвижения состояния "вперёд":
        Button buttonGetGameSet = new Button("Get the game set");

        // Контролы для продвижения состояния "назад":
        Button buttonForgetGameSet = new Button("Forget the game set");
        buttonForgetGameSet.setFocusTraversable(false);

        buttonGetGameSet.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.getGamePlaySet();
        });

        buttonForgetGameSet.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.forgetGamePlaySet();
        });

        setListsOfControlsAndAllDisable(
                List.of(buttonGetGameSet),
                List.of(buttonForgetGameSet)
        );
    }

    // 1
    @Override
    public void updateOnClose() {
        disableAllControls();
    }

    @Override
    public void updateOnOpen() {
        disableAllControls();
    }

    // 2
    @Override
    public void updateOnLogout() {
        disableAllControls();
    }

    @Override
    public void updateOnLogin() {
        disableAllControls();
    }

    // 3
    @Override
    public void updateOnForgetGameTypeSet() {
        disableAllControls();
    }

    @Override
    public void updateOnGetGameTypeSet() {
        disableAllControls();
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
        disableAllControls();
    }

    @Override
    public void updateOnSelectGameType() {
        setDisableControlsNextState(false);
    }

    // 5
    @Override
    public void updateOnForgetGamePlaySet() {
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnGetGamePlaySet() {
        setDisableControlsNextState(true);
    }
}
