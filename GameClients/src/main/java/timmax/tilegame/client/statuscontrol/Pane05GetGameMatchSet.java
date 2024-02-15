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
        doOnPrevPrevState();
    }

    @Override
    public void updateOnOpen() {
        doOnPrevPrevState();
    }

    // 2
    @Override
    public void updateOnLogout() {
        doOnPrevPrevState();
    }

    @Override
    public void updateOnLogin() {
        doOnPrevPrevState();
    }

    // 3
    @Override
    public void updateOnForgetGameTypeSet() {
        doOnPrevPrevState();
    }

    @Override
    public void updateOnGetGameTypeSet() {
        doOnPrevPrevState();
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
        doOnPrevPrevState();
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
    protected void doOnPrevPrevState() {
        disableAllControls();
    }

    @Override
    protected void doOnThisState() {
        setDisableControlsNextState(false);
    }

    @Override
    protected void doOnNextState() {
        setDisableControlsNextState(true);
    }
}
