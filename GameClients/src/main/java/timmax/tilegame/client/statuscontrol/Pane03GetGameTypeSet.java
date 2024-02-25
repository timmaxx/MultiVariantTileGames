package timmax.tilegame.client.statuscontrol;

import java.util.List;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.transport.TransportOfClient;

public class Pane03GetGameTypeSet extends AbstractConnectStatePane {
    public Pane03GetGameTypeSet(IModelOfClient iModelOfClient, TransportOfClient transportOfClient) {
        super(iModelOfClient, transportOfClient);

        // Контролы для продвижения состояния "вперёд":
        buttonNextState.setText("Get the game type set");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            iModelOfClient.getGameTypeSet();
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Forget the game type set");
        buttonPrevState.setFocusTraversable(false);
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            iModelOfClient.forgetGameTypeSet();
        });

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(buttonNextState),
                List.of(buttonPrevState)
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
