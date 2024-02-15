package timmax.tilegame.client.statuscontrol;

import java.util.List;

import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane03GetGameTypeSet extends AbstractConnectStatePane {
    public Pane03GetGameTypeSet(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        // Контролы для продвижения состояния "вперёд":
        buttonNextState.setText("Get the game type set");

        buttonNextState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.getGameTypeSet();
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Forget the game type set");
        buttonPrevState.setFocusTraversable(false);

        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.forgetGameTypeSet();
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
        doOnThisState();
    }

    // 3
    @Override
    public void updateOnForgetGameTypeSet() {
        doOnThisState();
    }

    @Override
    public void updateOnGetGameTypeSet() {
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
