package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;

import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.*;

public abstract class AbstractConnectStatePane extends HBox implements ObserverOnAbstractEvent {
    protected final TransportOfClient transportOfClient;

    protected Pane nextStatePane;
    protected Pane prevStatePane;

    protected Button nextStateButton;
    protected Button prevStateButton;
    private List<Region> nextStateControlsList;
    private List<Region> prevStateControlsList;

    public AbstractConnectStatePane(TransportOfClient transportOfClient) {
        this.transportOfClient = transportOfClient;

        nextStatePane = new Pane();
        prevStatePane = new Pane();
        nextStateButton = new Button();
        prevStateButton = new Button();
        nextStatePane.setPrefWidth(PANE_NEXT_STATE_PREF_WIDTH);
        prevStatePane.setPrefWidth(PANE_PREV_STATE_PREF_WIDTH);
        nextStateButton.setPrefWidth(BUTTON_NEXT_STATE_PREF_WIDTH);
        nextStateButton.setLayoutX(nextStatePane.getPrefWidth() - nextStateButton.getPrefWidth());
        prevStateButton.setPrefWidth(BUTTON_PREV_STATE_PREF_WIDTH);
        prevStateButton.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        prevStatePane.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN + BUTTON_PREV_STATE_PREF_WIDTH);

        getChildren().addAll(nextStatePane, nextStateButton, prevStateButton, prevStatePane);
    }

    public List<Region> getNextStateControlsList() {
        return nextStateControlsList;
    }

    public void setListsOfControlsAndAllDisable(
            List<Region> nextStateControlsList,
            List<Region> prevStateControlsList
    ) {
        this.nextStateControlsList = nextStateControlsList;
        this.prevStateControlsList = prevStateControlsList;
        nextStatePane.getChildren().clear();
        prevStatePane.getChildren().clear();
        nextStatePane.getChildren().addAll(nextStateControlsList);
        prevStatePane.getChildren().addAll(prevStateControlsList);
        disableAllControls();

        transportOfClient.getLocalClientStateAutomaton().addCallBackOnIncomingTransportPackageEvent(this);
    }

    protected void disableAllControls() {
        for (Region control : nextStateControlsList) {
            control.setDisable(true);
        }
        nextStateButton.setDisable(true);
        for (Region control : prevStateControlsList) {
            control.setDisable(true);
        }
        prevStateButton.setDisable(true);
    }

    protected void setDisableNextStateControls(boolean disableNextStateControls) {
        for (Region control : nextStateControlsList) {
            control.setDisable(disableNextStateControls);
        }
        nextStateButton.setDisable(disableNextStateControls);
        for (Region control : prevStateControlsList) {
            control.setDisable(!disableNextStateControls);
        }
        prevStateButton.setDisable(!disableNextStateControls);
    }

    //
    protected void doOnPrevState() {
        disableAllControls();
    }

    protected void doOnThisState() {
        setDisableNextStateControls(false);
    }

    protected void doOnNextState() {
        setDisableNextStateControls(true);
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
    public void updateOnSetUser() {
    }

    // 4
    @Override
    public void updateOnSetGameType() {
    }

    // 6
    @Override
    public void updateOnSetGameMatch() {
    }

    // 7
    @Override
    public void updateOnSetGameMatchPlaying() {
    }
}
