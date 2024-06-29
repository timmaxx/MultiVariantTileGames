package timmax.tilegame.client.statuscontrol;

import java.util.List;

import timmax.tilegame.transport.TransportOfClient;

public class Pane03GetGameTypeSet<ClientId> extends AbstractConnectStatePane<ClientId> {
    public Pane03GetGameTypeSet(TransportOfClient<ClientId> transportOfClient) {
        super(transportOfClient);

        // Контролы для продвижения состояния "вперёд":
        buttonNextState.setText("Get the game type set");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            transportOfClient.giveGameTypeSet();
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Forget the game type set");
        buttonPrevState.setFocusTraversable(false);
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            transportOfClient.forgetGameTypeSet();
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
}
