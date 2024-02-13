package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.layout.Pane;

import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane07GameMatchPlaying extends AbstractConnectStatePane {

    public Pane07GameMatchPlaying(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        // Контролы для продвижения состояния "вперёд":
        buttonNextState.setText("Start new game");
        // ToDo: по общему правилу 'buttonNewGame.setFocusTraversable(false);' следует закомметировать,
        //       но т.к. кнопка 'buttonNewGame' пока не делается не активной после нажатия
        //       (т.к. от сервера не приходит сообщение о начале новой игры), то пусть пока так:
        buttonNextState.setFocusTraversable(false);

        buttonNextState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.startGameMatchPlaying();
        });

        // Контролы для продвижения состояния "назад":
        Pane pane = new Pane();
        pane.setPrefWidth(1000);
        pane.setPrefHeight(300);

        buttonPrevState.setText("Quit the game");

        buttonPrevState.setFocusTraversable(false); // Это в любом случае д.б.

        buttonPrevState.setOnAction(event -> {
            // disableAllControls();
            // multiGameWebSocketClientManyTimesUse.quitGame();
            System.out.println("Quit the game");
        });

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(buttonNextState),
                List.of(pane, buttonPrevState)
        );

        enableAllControls();
        pane.setDisable(false);
        buttonPrevState.setDisable(false);
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

    // 5
    @Override
    public void updateOnForgetGameMatchSet() {
        disableAllControls();
    }

    @Override
    public void updateOnGetGameMatchSet() {
        disableAllControls();
    }

    // 6
    @Override
    public void updateOnForgetGameMatch() {
        disableAllControls();
    }

    @Override
    public void updateOnSelectGameMatch() {
        System.out.println("updateOnSelectGameMatch");
        setDisableControlsNextState(false);
    }

    // 7
    @Override
    public void updateOnStartGameMatchPlaying() {
        System.out.println("updateOnStartGameMatchPlaying");
        // ToDo: Создать выборки и контролы, соответствующие типу игры, отправить серверу сообщения об этом.
        //       Но пока делаем одну универсальную выборку - контрол - основное поле игры.
        //       ...
        // ViewMainFieldJfx viewMainFieldJfx = new ViewMainFieldJfx(iModelOfClient, baseController);
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnStopGameMatchPlaying() {
        // ToDo: ???
        System.out.println("updateOnStopGameMatchPlaying");
        setDisableControlsNextState(true);
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
