package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane07GameMatchPlaying extends AbstractConnectStatePane {
    private final Pane pane;

    public Pane07GameMatchPlaying(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        Button buttonNewGame = new Button("Start new game");

        pane = new Pane();
        pane.setPrefWidth(1000);
        pane.setPrefHeight(300);

        Button buttonQuitGame = new Button("Quit the game");

        // ToDo: по общему правилу 'buttonNewGame.setFocusTraversable(false);' следует закомметировать,
        //       но т.к. кнопка 'buttonNewGame' пока не делается не активной после нажатия
        //       (т.к. от сервера не приходит сообщение о начале новой игры), то пусть пока так:
        buttonNewGame.setFocusTraversable(false);
        buttonQuitGame.setFocusTraversable(false); // Это в любом случае д.б.

        buttonNewGame.setOnAction(event -> {
            // disableAllControls();
            multiGameWebSocketClientManyTimesUse.createNewGame();
        });

        buttonQuitGame.setOnAction(event -> {
            // disableAllControls();
            // multiGameWebSocketClientManyTimesUse.quitGame();
            System.out.println("Quit the game");
        });

        setListsOfControlsAndAllDisable(
                List.of(buttonNewGame),
                List.of(pane, buttonQuitGame)
        );

        enableAllControls();
        pane.setDisable(false);
        buttonQuitGame.setDisable(false);
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

    /*
        @Override
        public void updateOnSelectGameType() {
            disableAllControls();
            pane.getChildren().clear();
            // ToDo: В этой реализации нет заголовка игры (и в будущем других её свойств).
            pane.getChildren().addAll(new GameClientPaneJfx(multiGameWebSocketClientManyTimesUse, multiGameWebSocketClientManyTimesUse.getMultiGameWebSocketClient()));

            setDisableControlsNextState(false);

            {   // Эти вызовы пока нужны, т.к. нет вызова updateOnCreateNewGame().
                enableAllControls();
                pane.setDisable(false);
                enableAllControls();
            }
        }
    */
    @Override
    public void updateOnSelectGameType() {
        disableAllControls();
    }

    // 7
    @Override
    public void updateOnStartGameMatchPlaying() {
        // ToDo: ???
    }

    @Override
    public void updateOnStopGameMatchPlaying() {
        // ToDo: ???
    }

    // X
    @Override
    public void updateOnAddView() {
        // Этот метод не вызывается...
        System.out.println("updateOnAddView()");
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnCreateNewGame() {
        // Этот метод не вызывается...
        System.out.println("updateOnCreateNewGame()");
        setDisableControlsNextState(true);
    }

    @Override
    public void updateOnCloseGame() {
        // setDisableControlsNextState(true);
    }

    @Override
    public void updateOnGameEvent() {
        System.out.println("updateOnGameEvent()");
        //setDisableControlsNextState(true);
    }
}
