package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.application.Platform;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;

import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.basemodel.protocol.TypeOfEvent;
import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

import static timmax.tilegame.basemodel.protocol.TypeOfEvent.*;

public abstract class AbstractConnectStatePane extends HBox implements ObserverOnAbstractEvent {
    MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse;
    private List<Region> listOfControlsNextState;
    private List<Region> listOfControlsPrevState;
    protected LocalClientState localClientState;

    public AbstractConnectStatePane(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        this.multiGameWebSocketClientManyTimesUse = multiGameWebSocketClientManyTimesUse;
        this.localClientState = multiGameWebSocketClientManyTimesUse.getLocalClientState();
    }

    public void setListsOfControlsAndAllDisable(
            List<Region> listOfControlsNextState,
            List<Region> listOfControlsPrevState
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
        for (Region control : listOfControlsNextState) {
            control.setDisable(true);
        }
        for (Region control : listOfControlsPrevState) {
            control.setDisable(true);
        }
    }

    // ToDo: Это временный метод. Потом удалить.
    protected void enableAllControls() {
        for (Region control : listOfControlsNextState) {
            control.setDisable(false);
        }
        for (Region control : listOfControlsPrevState) {
            control.setDisable(false);
        }
    }

    protected void setDisableControlsNextState(boolean disableControlsNextState) {
        for (Region control : listOfControlsNextState) {
            control.setDisable(disableControlsNextState);
        }
        for (Region control : listOfControlsPrevState) {
            control.setDisable(!disableControlsNextState);
        }
    }

    protected void updateOnClose() {
    }

    protected void updateOnOpen() {
    }

    protected void updateOnLogout() {
    }

    protected void updateOnLogin() {
    }

    protected void updateOnForgetGameTypeSet() {
    }

    protected void updateOnGetGameTypeSet() {
    }

    protected void updateOnForgetGameType() {
    }

    protected void updateOnSelectGameType() {
    }

    protected void updateOnCreateNewGame() {
    }

    protected void updateOnCloseGame() {
    }

    protected void updateOnAddView() {
    }

    protected void updateOnGameEvent() {
    }

    //  Описанное было обнаружено при работе с Pane04SelectGameType
    //  Если ранее comboBoxGameTypeSet уже было заполнено (т.е. вызывался updateOnGetGameTypeSet)
    //  и не использовать здесь Platform.runLater(), то возникнет исключение:
    //  Not on FX application thread
    //  Например:
    //  Exception in thread "WebSocketConnectReadThread-25" java.lang.IllegalStateException: Not on FX application thread; currentThread = WebSocketConnectReadThread-25

    //  ToDo: TypeOfEvent это перечисление. Нужно через ООП сделать и избавиться от перечисление.
    public final void update(TypeOfEvent typeOfEvent) {
        Platform.runLater(() -> {
            if (typeOfEvent == CLOSE) {
                updateOnClose();
            } else if (typeOfEvent == OPEN) {
                updateOnOpen();
            } else if (typeOfEvent == LOGOUT) {
                updateOnLogout();
            } else if (typeOfEvent == LOGIN) {
                updateOnLogin();
            } else if (typeOfEvent == FORGET_GAME_TYPE_SET) {
                updateOnForgetGameTypeSet();
            } else if (typeOfEvent == GET_GAME_TYPE_SET) {
                updateOnGetGameTypeSet();
            } else if (typeOfEvent == FORGET_GAME_TYPE) {
                updateOnForgetGameType();
            } else if (typeOfEvent == SELECT_GAME_TYPE) {
                // Сюда есть вход.
                System.out.println("before updateOnSelectGameType() in void update(TypeOfEvent typeOfEvent) in class AbstractConnectStatePane");
                updateOnSelectGameType();
            } else if (typeOfEvent == CREATE_NEW_GAME) {
                // Сюда входа нет, т.к. сервер не генерирует "общего" события - только игровое генерирует...
                System.out.println("before updateOnCreateNewGame() in void update(TypeOfEvent typeOfEvent) in class AbstractConnectStatePane");
                updateOnCreateNewGame();
            } else if (typeOfEvent == CLOSE_GAME) {
                updateOnCloseGame();
            } else if (typeOfEvent == ADD_VIEW) {
                updateOnAddView();
            } else if (typeOfEvent == GAME_EVENT) {
                // Сюда входа нет...
                System.out.println("before updateOnGameEvent() in void update(TypeOfEvent typeOfEvent) in class AbstractConnectStatePane");
                updateOnGameEvent();
            }
        });
    }
}
