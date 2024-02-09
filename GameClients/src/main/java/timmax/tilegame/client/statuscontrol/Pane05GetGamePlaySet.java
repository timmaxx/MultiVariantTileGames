package timmax.tilegame.client.statuscontrol;

import java.util.List;

import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane05GetGamePlaySet extends AbstractConnectStatePane {
    public Pane05GetGamePlaySet(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        // Контролы для продвижения состояния "вперёд":
        buttonNextState.setText("Get the game set");

        buttonNextState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.getGameMatchSet();
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Forget the game set");
        buttonPrevState.setFocusTraversable(false);

        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.forgetGameMatchSet();
        });

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(buttonNextState),
                List.of(buttonPrevState)
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
    public void updateOnForgetGameMatchSet() {
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnGetGameMatchSet() {
        setDisableControlsNextState(true);
    }
}
