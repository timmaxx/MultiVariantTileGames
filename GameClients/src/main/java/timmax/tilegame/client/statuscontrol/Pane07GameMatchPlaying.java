package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.layout.Pane;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.guiengine.jfx.GameClientPaneJfx;
import timmax.tilegame.transport.TransportOfClient;

public class Pane07GameMatchPlaying extends AbstractConnectStatePane {
/*
    private Label labelExample;
    private TextField textFieldExample;
*/
    private final Pane paneGameViewsAndControls;

    public Pane07GameMatchPlaying(IModelOfClient iModelOfClient, TransportOfClient transportOfClient) {
        super(iModelOfClient, transportOfClient);

        // Контролы для продвижения состояния "вперёд":
        buttonNextState.setText("Start the game match");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            iModelOfClient.startGameMatchPlaying();
        });

        // Контролы для продвижения состояния "назад":
        paneGameViewsAndControls = new Pane();
        paneGameViewsAndControls.setPrefWidth(1000);
        paneGameViewsAndControls.setPrefHeight(300);

        buttonPrevState.setText("Stop the game match");
        buttonPrevState.setFocusTraversable(false); // Это в любом случае д.б.
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            iModelOfClient.stopGameMatchPlaying();
        });

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(buttonNextState),
                List.of(paneGameViewsAndControls, buttonPrevState)
        );
    }

    // Implemented methods of interface ObserverOnAbstractEvent
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
        paneGameViewsAndControls.getChildren().clear();
        paneGameViewsAndControls.getChildren().add(new GameClientPaneJfx(iModelOfClient, transportOfClient));
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
}
