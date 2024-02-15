package timmax.tilegame.client.statuscontrol;

import java.util.List;

import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane05GetGameMatchSet extends AbstractConnectStatePane {
    public Pane05GetGameMatchSet(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        // Контролы для продвижения состояния "вперёд":
        buttonNextState.setText("Get the game match set");

        buttonNextState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.getGameMatchSet();
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Forget the game match set");
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
        doOnPrevState();
    }

    @Override
    public void updateOnOpen() {
        doOnPrevState();
    }

    // 2
    @Override
    public void updateOnLogout() {
        doOnPrevState();
    }

    @Override
    public void updateOnLogin() {
        doOnPrevState();
    }

    // 3
    @Override
    public void updateOnForgetGameTypeSet() {
        doOnPrevState();
    }

    @Override
    public void updateOnGetGameTypeSet() {
        doOnPrevState();
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
        doOnPrevState();
    }

    @Override
    public void updateOnSelectGameType() {
        doOnThisState();
    }

    // 5
    @Override
    public void updateOnForgetGameMatchSet() {
        doOnThisState();
    }

    @Override
    public void updateOnGetGameMatchSet() {
        doOnNextState();
    }

    //
    @Override
    protected void doOnThisState() {
        setDisableControlsNextState(false);
    }

    @Override
    protected void doOnNextState() {
        setDisableControlsNextState(true);
    }
}
