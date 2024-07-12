package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;

import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.transport.TransportOfClient;

public abstract class AbstractConnectStatePane extends HBox implements ObserverOnAbstractEvent {
    protected final static int BUTTON_NEXT_STATE_PREF_WIDTH = 160;
    protected final static int BUTTON_PREV_STATE_PREF_WIDTH = 160;
    protected final static int PANE_NEXT_STATE_PREF_WIDTH = 300;
    protected final static int PANE_PREV_STATE_PREF_WIDTH = 0;

    protected final static int LAYOUT_X_OF_FIRST_COLUMN = 0;
    protected final static int LAYOUT_X_OF_SECOND_COLUMN = 100;
    protected final static int LAYOUT_Y_OF_FIRST_ROW = 0;
    protected final static int DIFFERENCE_OF_LAYOUT_Y = 30;

    protected final TransportOfClient transportOfClient;

    protected Pane paneNextState;
    protected Pane panePrevState;

    protected Button buttonNextState;
    protected Button buttonPrevState;
    private List<Region> listOfControlsNextState;
    private List<Region> listOfControlsPrevState;

    public AbstractConnectStatePane(TransportOfClient transportOfClient) {
        this.transportOfClient = transportOfClient;

        paneNextState = new Pane();
        panePrevState = new Pane();
        buttonNextState = new Button();
        buttonPrevState = new Button();
        paneNextState.setPrefWidth(PANE_NEXT_STATE_PREF_WIDTH);
        panePrevState.setPrefWidth(PANE_PREV_STATE_PREF_WIDTH);
        buttonNextState.setPrefWidth(BUTTON_NEXT_STATE_PREF_WIDTH);
        buttonNextState.setLayoutX(paneNextState.getPrefWidth() - buttonNextState.getPrefWidth());
        buttonPrevState.setPrefWidth(BUTTON_PREV_STATE_PREF_WIDTH);
        buttonPrevState.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        panePrevState.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN + BUTTON_PREV_STATE_PREF_WIDTH);

        getChildren().addAll(paneNextState, buttonNextState, buttonPrevState, panePrevState);
    }

    public List<Region> getListOfControlsNextState() {
        return listOfControlsNextState;
    }

    public void setListsOfControlsAndAllDisable(
            List<Region> listOfControlsNextState,
            List<Region> listOfControlsPrevState
    ) {
        this.listOfControlsNextState = listOfControlsNextState;
        this.listOfControlsPrevState = listOfControlsPrevState;
        paneNextState.getChildren().clear();
        panePrevState.getChildren().clear();
        paneNextState.getChildren().addAll(listOfControlsNextState);
        panePrevState.getChildren().addAll(listOfControlsPrevState);
        disableAllControls();

        transportOfClient.getLocalClientStateAutomaton().addCallBackOnIncomingTransportPackageEvent(this);
    }

    protected void disableAllControls() {
        for (Region control : listOfControlsNextState) {
            control.setDisable(true);
        }
        buttonNextState.setDisable(true);
        for (Region control : listOfControlsPrevState) {
            control.setDisable(true);
        }
        buttonPrevState.setDisable(true);
    }

    protected void setDisableControlsNextState(boolean disableControlsNextState) {
        for (Region control : listOfControlsNextState) {
            control.setDisable(disableControlsNextState);
        }
        buttonNextState.setDisable(disableControlsNextState);
        for (Region control : listOfControlsPrevState) {
            control.setDisable(!disableControlsNextState);
        }
        buttonPrevState.setDisable(!disableControlsNextState);
    }

    //
    protected void doOnPrevState() {
        disableAllControls();
    }

    protected void doOnThisState() {
        setDisableControlsNextState(false);
    }

    protected void doOnNextState() {
        setDisableControlsNextState(true);
    }

    // Implemented methods of interface ObserverOnAbstractEvent

    //  ToDo: Пока не сильно красиво сделано с большим перечнем разнообразных updateXXX().
    //        Пересмотреть архитектуру, внести изменения в код.

    // Все методы здесь для единообразия расположены в порядке:
    // - от 1 к 7 (и далее),
    // - внутри этого "к предыдущему состоянию (x.0)", "к следующему (x.1)".
    // ToDo: уменьшить количество методов. Их можно разложить на группы:
    // 1. Все, кроме п. 2 и п. 3. Закрывает все контролы.
    // 2. Два предпоследних
    //   1. Инициализирует контрол(ы) и открывает контролы перехода к следующему состоянию.
    //   2. Открывает контролы перехода к следующему состоянию.
    // 3. Последний метод. Открывает контролы перехода к предыдующему состоянию.

    // 1
    @Override
    public void updateOnClose() {
    }

    // ToDo: методы 1.1 и 2.0 (и вообще x.1 и x+1.0) это почти одно и тоже.
    //       Отличие только в инициализации в методах. Это нужно как-то свернуть.
    @Override
    public void updateOnOpen() {
    }

    // 2
    @Override
    public void updateOnForgetUser() {
    }

    @Override
    public void updateOnSetUser() {
    }

    // 3
    @Override
    public void updateOnForgetGameTypeSet() {
    }

    @Override
    public void updateOnSetGameTypeSet() {
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
    }

    @Override
    public void updateOnSetGameType() {
    }

    // 5
    @Override
    public void updateOnForgetGameMatchSet() {
    }

    @Override
    public void updateOnSetGameMatchSet() {
    }

    // 6
    @Override
    public void updateOnForgetGameMatch() {
    }

    @Override
    public void updateOnSetGameMatch() {
    }

    // 7
    @Override
    public void updateOnForgetGameMatchPlaying() {
    }

    @Override
    public void updateOnSetGameMatchPlaying() {
    }
}
