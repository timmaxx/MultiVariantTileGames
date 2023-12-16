package timmax.tilegame.client.statuscontrol;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import timmax.tilegame.game.sokoban.jfx.SokobanClientPaneJfx;
import timmax.tilegame.websocket.client.MultiGameWebSocketClientManyTimesUse;

import java.util.List;

public class Pane09Gaming extends AbstractConnectStatePane {
    private final Pane pane;

    public Pane09Gaming(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        Button buttonNewGame = new Button("Start new game");

        pane = new Pane();
        pane.setPrefWidth(200);
        pane.setPrefHeight(300);
        Button buttonQuitGame = new Button("Quit the game");

        buttonNewGame.setOnAction( event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.createNewGame();
        });

        buttonQuitGame.setOnAction(event -> {
            disableAllControls();
            // multiGameWebSocketClientManyTimesUse.quitGame();
            System.out.println("Quit the game");
        });

        setListsOfControlsAndAllDisable(
                List.of(buttonNewGame),
                List.of(pane, buttonQuitGame)
        );
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
        // disableAllControls();
        pane.getChildren().clear();
        pane.getChildren().addAll(new SokobanClientPaneJfx((Stage) getScene().getWindow(), multiGameWebSocketClientManyTimesUse, null));
        setDisableControlsNextState(false);
    }

    @Override
    protected void updateOnAddView() {
        System.out.println("updateOnAddView()");
        // setDisableControlsNextState(false);
    }

    @Override
    protected void updateOnCreateNewGame() {
        setDisableControlsNextState(true);
    }

    @Override
    protected void updateOnCloseGame() {
        // setDisableControlsNextState(true);
    }
}