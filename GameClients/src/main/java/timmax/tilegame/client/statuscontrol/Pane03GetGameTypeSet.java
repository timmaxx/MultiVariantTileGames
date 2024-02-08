package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Button;

import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane03GetGameTypeSet extends AbstractConnectStatePane {
    public Pane03GetGameTypeSet(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        // Контролы для продвижения состояния "вперёд":
        Button buttonGetGameTypeSet = new Button("Get the game type set");

        buttonGetGameTypeSet.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.getGameTypeSet();
        });

        // Контролы для продвижения состояния "назад":
        Button buttonForgetGameTypeSet = new Button("Forget the game type set");
        buttonForgetGameTypeSet.setFocusTraversable(false);

        buttonForgetGameTypeSet.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.forgetGameTypeSet();
        });

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(buttonGetGameTypeSet),
                List.of(buttonForgetGameTypeSet)
        );
    }

    @Override
    public void updateOnClose() {
        disableAllControls();
    }

    @Override
    public void updateOnOpen() {
        disableAllControls();
    }

    @Override
    public void updateOnLogout() {
        disableAllControls();
    }

    @Override
    public void updateOnLogin() {
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnForgetGameTypeSet() {
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnGetGameTypeSet() {
        setDisableControlsNextState(true);
    }
}
