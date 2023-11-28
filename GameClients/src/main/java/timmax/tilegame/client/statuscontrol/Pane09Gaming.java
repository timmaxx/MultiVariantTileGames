package timmax.tilegame.client.statuscontrol;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.game.sokoban.jfx.SokobanClientPaneJfx;
import timmax.tilegame.websocket.client.MultiGameWebSocketClientManyTimesUse;

import java.util.List;

public class Pane09Gaming extends AbstractConnectStatePane {
    private final Pane pane;


    public Pane09Gaming(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        pane = new Pane();
        pane.setPrefWidth(200);
        pane.setPrefHeight(300);
        Button buttonQuitGame = new Button("Quit the game");

        buttonQuitGame.setOnAction(event -> {
            disableAllControls();
            // multiGameWebSocketClientManyTimesUse.quitGame();
            System.out.println("Quit the game");
        });

        setListsOfControlsAndAllDisable(
                List.of(),
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
        // setDisableControlsNextState(false);
        disableAllControls();
    }

    @Override
    protected void updateOnForgetGameType() {
        // setDisableControlsNextState(false);
        disableAllControls();
    }

    @Override
    protected void updateOnSelectGameType() {
        //setDisableControlsNextState(false);
        disableAllControls();
        pane.getChildren().clear();

        // BaseModel baseModel;

        pane.getChildren().addAll(new SokobanClientPaneJfx((Stage) getScene().getWindow(), multiGameWebSocketClientManyTimesUse, null));
        // pane.getChildren().addAll(new SokobanClientPaneJfx((Stage) getScene().getWindow(), multiGameWebSocketClientManyTimesUse, multiGameWebSocketClientManyTimesUse));
        // public SokobanClientPaneJfx(Stage primaryStage, BaseModel baseModel, TransportOfController transportOfController)
    }

    @Override
    protected void updateOnAddView() {
        setDisableControlsNextState(false);
    }
}