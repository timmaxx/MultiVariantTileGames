package timmax.tilegame.client.statuscontrol;

import java.util.List;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.transport.TransportOfClient;

public class Pane05GetGameMatchSet extends AbstractConnectStatePane {
    public Pane05GetGameMatchSet(IModelOfClient iModelOfClient, TransportOfClient transportOfClient) {
        super(iModelOfClient, transportOfClient);

        // Контролы для продвижения состояния "вперёд":
        buttonNextState.setText("Get the game match set");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            iModelOfClient.getGameMatchSet();
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Forget the game match set");
        buttonPrevState.setFocusTraversable(false);
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            iModelOfClient.forgetGameMatchSet();
        });

        paneNextState.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * 1);
        paneNextState.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * 1);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(),
                List.of()
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
}
