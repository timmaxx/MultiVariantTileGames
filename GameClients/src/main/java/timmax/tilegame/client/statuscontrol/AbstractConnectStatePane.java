package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.application.Platform;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;

import timmax.tilegame.basemodel.protocol.ClientState;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.TypeOfTransportPackage;
import timmax.tilegame.websocket.client.MultiGameWebSocketClientManyTimesUse;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public abstract class AbstractConnectStatePane extends HBox implements ObserverOnAbstractEvent {
    MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse;
    private List<Control> listOfControlsNextState;
    private List<Control> listOfControlsPrevState;
    protected ClientState clientState;

    public AbstractConnectStatePane(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        this.multiGameWebSocketClientManyTimesUse = multiGameWebSocketClientManyTimesUse;
        this.clientState = multiGameWebSocketClientManyTimesUse.getClientState();
    }

    public void setListsOfControlsAndAllDisable(
            List<Control> listOfControlsNextState,
            List<Control> listOfControlsPrevState
    ) {
        this.listOfControlsNextState = listOfControlsNextState;
        this.listOfControlsPrevState = listOfControlsPrevState;
        getChildren().clear();
        getChildren().addAll(listOfControlsNextState);
        getChildren().addAll(listOfControlsPrevState);
        disableAllControls();

        multiGameWebSocketClientManyTimesUse.addCallBackOnIncomingTransportPackageEvent(this);
    }

    protected void disableAllControls() {
        for (Control control : listOfControlsNextState) {
            control.setDisable(true);
        }
        for (Control control : listOfControlsPrevState) {
            control.setDisable(true);
        }
    }

    protected void setDisableControlsNextState(boolean disableControlsNextState) {
        for (Control control : listOfControlsNextState) {
            control.setDisable(disableControlsNextState);
        }
        for (Control control : listOfControlsPrevState) {
            control.setDisable(!disableControlsNextState);
        }
    }

    public void updateOnClose() {
    }

    public void updateOnOpen() {
    }

    public void updateOnLogout() {
    }

    public void updateOnLogin() {
    }

    public void updateOnForgetGameTypeSet() {
    }

    public void updateOnGetGameTypeSet() {
    }

    public void updateOnForgetGameType() {
    }

    public void updateOnSelectGameType() {
    }

    //  Описанное было обнаружено при работе с Pane04SelectGameType
    //  Если ранее comboBoxGameTypeSet уже было заполнено (т.е. вызывался updateOnGetGameTypeSet)
    //  и не использовать здесь Platform.runLater(), то возникнет исключение:
    //  Not on FX application thread
    //  Например:
    //  Exception in thread "WebSocketConnectReadThread-25" java.lang.IllegalStateException: Not on FX application thread; currentThread = WebSocketConnectReadThread-25
    public void update(TypeOfTransportPackage typeOfTransportPackage) {
        Platform.runLater(() -> {
            if (typeOfTransportPackage == CLOSE) {
                updateOnClose();
            } else if (typeOfTransportPackage == OPEN) {
                updateOnOpen();
            } else if (typeOfTransportPackage == LOGOUT) {
                updateOnLogout();
            } else if (typeOfTransportPackage == LOGIN) {
                updateOnLogin();
            } else if (typeOfTransportPackage == FORGET_GAME_TYPE_SET) {
                updateOnForgetGameTypeSet();
            } else if (typeOfTransportPackage == GET_GAME_TYPE_SET) {
                updateOnGetGameTypeSet();
            } else if (typeOfTransportPackage == FORGET_GAME_TYPE) {
                updateOnForgetGameType();
            } else if (typeOfTransportPackage == SELECT_GAME_TYPE) {
                updateOnSelectGameType();
            }
        });
    }
}