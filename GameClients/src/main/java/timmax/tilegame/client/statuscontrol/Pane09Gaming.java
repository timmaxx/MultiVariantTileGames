package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;
import timmax.tilegame.websocket.client.MultiGameWebSocketClientManyTimesUse;

public class Pane09Gaming extends AbstractConnectStatePane {
    private final Pane pane;

    public Pane09Gaming(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        Button buttonNewGame = new Button("Start new game");

        pane = new Pane();
        pane.setPrefWidth(1000);
        pane.setPrefHeight(300);

        Button buttonQuitGame = new Button("Quit the game");

        buttonNewGame.setOnAction(event -> {
            //disableAllControls();
            multiGameWebSocketClientManyTimesUse.createNewGame();
        });

        buttonQuitGame.setOnAction(event -> {
            //disableAllControls();
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

    @Override
    protected void updateOnClose() {
        disableAllControls();
    }

    @Override
    protected void updateOnOpen() {
        disableAllControls();
    }

    @Override
    protected void updateOnLogout() {
        disableAllControls();
    }

    @Override
    protected void updateOnLogin() {
        disableAllControls();
    }

    @Override
    protected void updateOnForgetGameTypeSet() {
        disableAllControls();
    }

    @Override
    protected void updateOnGetGameTypeSet() {
        disableAllControls();
    }

    @Override
    protected void updateOnForgetGameType() {
        disableAllControls();
    }

    @Override
    protected void updateOnSelectGameType() {
        disableAllControls();
        pane.getChildren().clear();
        // ToDo: В этой реализации нет заголовка игры (и в будущем других её свойств).
        pane.getChildren().addAll(new GameClientPaneJfx((Stage) getScene().getWindow(), multiGameWebSocketClientManyTimesUse, multiGameWebSocketClientManyTimesUse.getMultiGameWebSocketClient()));

        setDisableControlsNextState(false);

        {   // Эти вызовы пока нужны, т.к. нет вызова updateOnCreateNewGame().
            enableAllControls();
            pane.setDisable(false);
            enableAllControls();
        }

    }

    @Override
    protected void updateOnAddView() {
        // Этот метод не вызывается...
        System.out.println("updateOnAddView()");
        setDisableControlsNextState(false);
    }

    @Override
    protected void updateOnCreateNewGame() {
        // Этот метод не вызывается...
        System.out.println("updateOnCreateNewGame()");
        setDisableControlsNextState(true);
    }

    @Override
    protected void updateOnCloseGame() {
        // setDisableControlsNextState(true);
    }

    @Override
    protected void updateOnGameEvent() {
        System.out.println("updateOnGameEvent()");
        //setDisableControlsNextState(true);
    }
}
