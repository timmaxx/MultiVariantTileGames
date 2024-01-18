package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;

import timmax.tilegame.basemodel.protocol.client.LocalClientState;
import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public abstract class AbstractConnectStatePane extends HBox implements ObserverOnAbstractEvent {
    MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse;
    private List<Region> listOfControlsNextState;
    private List<Region> listOfControlsPrevState;
    protected LocalClientState localClientState;

    public AbstractConnectStatePane(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        this.multiGameWebSocketClientManyTimesUse = multiGameWebSocketClientManyTimesUse;
        this.localClientState = multiGameWebSocketClientManyTimesUse.getLocalClientState();
    }

    // Overriden method from interface ObserverOnAbstractEvent
    // 1
    @Override
    public void updateOnClose() {
    }

    @Override
    public void updateOnOpen() {
    }

    // 2
    @Override
    public void updateOnLogout() {
    }

    @Override
    public void updateOnLogin() {
    }

    // 3
    @Override
    public void updateOnForgetGameTypeSet() {
    }

    @Override
    public void updateOnGetGameTypeSet() {
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
    }

    @Override
    public void updateOnSelectGameType() {
    }

    // 5
/*
    @Override
    public void updateOnForgetGamePlaySet() {
    }
*/
    @Override
    public void updateOnGetGamePlaySet() {
    }

    // X
    @Override
    public void updateOnCreateNewGame() {
    }

    @Override
    public void updateOnCloseGame() {
    }

    @Override
    public void updateOnAddView() {
    }

    @Override
    public void updateOnGameEvent() {
    }

    // Own methods
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

/*
    //  Комментарии пока оставил, т.к.:
    //  1. Пока не сильно красиво сделано с большим перечнем разнообразных updateXXX().
    //  2. См. комментарии "Сюда есть вход." и "Сюда входа нет".

    //  Описанное было обнаружено при работе с Pane04SelectGameType
    //  Если ранее comboBoxGameTypeSet уже было заполнено (т.е. вызывался updateOnGetGameTypeSet)
    //  и не использовать здесь Platform.runLater(), то возникнет исключение:
    //  Not on FX application thread
    //  Например:
    //  Exception in thread "WebSocketConnectReadThread-25" java.lang.IllegalStateException: Not on FX application thread; currentThread = WebSocketConnectReadThread-25

    @Override
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
    */
}
