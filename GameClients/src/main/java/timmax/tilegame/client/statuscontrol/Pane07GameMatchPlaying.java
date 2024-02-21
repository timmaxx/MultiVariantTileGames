package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.layout.Pane;

import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;
import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;

public class Pane07GameMatchPlaying extends AbstractConnectStatePane {
    private final Pane pane;

    public Pane07GameMatchPlaying(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        // Контролы для продвижения состояния "вперёд":
        buttonNextState.setText("Start the game match");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.startGameMatchPlaying();
        });

        // Контролы для продвижения состояния "назад":
        pane = new Pane();
        pane.setPrefWidth(1000);
        pane.setPrefHeight(300);

        buttonPrevState.setText("Stop the game match");
        buttonPrevState.setFocusTraversable(false); // Это в любом случае д.б.
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.stopGameMatchPlaying();
        });

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(buttonNextState),
                List.of(pane, buttonPrevState)
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
        doOnPrevState();
        pane.getChildren().clear();
        // ToDo: Передаётся несколько параметров, которые получается из multiGameWebSocketClientManyTimesUse.
        //       Уменьшить количество параметров.
        pane.getChildren().add(new GameClientPaneJfx(
                multiGameWebSocketClientManyTimesUse,
                multiGameWebSocketClientManyTimesUse.getMultiGameWebSocketClient()
        ));
    }

    // 5
    @Override
    public void updateOnForgetGameMatchSet() {
        doOnPrevState();
    }

    @Override
    public void updateOnGetGameMatchSet() {
        doOnPrevState();
    }

    // 6
    @Override
    public void updateOnForgetGameMatch() {
        doOnPrevState();
    }

    @Override
    public void updateOnSelectGameMatch() {
        doOnThisState();
    }

    // 7
    @Override
    public void updateOnStopGameMatchPlaying() {
        doOnThisState();
    }

    @Override
    public void updateOnStartGameMatchPlaying() {
        doOnNextState();
    }

    // X
    @Override
    public void updateOnCreateNewGame() {
        // Этот метод не вызывается. Да и нужен-ли он?
        System.out.println("updateOnCreateNewGame()");
    }

    @Override
    public void updateOnCloseGame() {
        // Этот метод не вызывается. Да и нужен-ли он?
        System.out.println("updateOnCloseGame()");
    }

    @Override
    public void updateOnGameEvent() {
        // Этот метод не вызывается. Да и нужен-ли он?
        System.out.println("updateOnGameEvent()");
    }
}
