package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;

import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.transport.TransportOfClient;

public abstract class AbstractConnectStatePane extends HBox implements ObserverOnAbstractEvent {
    // X - составляющие
    public final static int LAYOUT_X_OF_FIRST_COLUMN = 0;
    public final static int LAYOUT_X_OF_SECOND_COLUMN = 100;

    public final static int PANE_NEXT_STATE_PREF_WIDTH = 300;

    public final static int BUTTON_NEXT_STATE_PREF_WIDTH = 200;
    public final static int BUTTON_PREV_STATE_PREF_WIDTH = 200;

    // Y - составляющие
    public final static int LAYOUT_Y_OF_FIRST_ROW = 0;
    public final static int DIFFERENCE_OF_LAYOUT_Y = 30;

    protected final TransportOfClient transportOfClient;

    protected Pane nextStatePane;
    protected Button nextStateButton;
    protected Button prevStateButton;
    protected Pane prevStatePane;

    private List<Region> nextStateControlsList;
    private List<Region> prevStateControlsList;

    public AbstractConnectStatePane(TransportOfClient transportOfClient) {
        this.transportOfClient = transportOfClient;

        nextStatePane = new Pane();
        nextStatePane.setMinWidth(PANE_NEXT_STATE_PREF_WIDTH);
        nextStatePane.setMaxWidth(PANE_NEXT_STATE_PREF_WIDTH);

        nextStateButton = new Button();
        nextStateButton.setMinWidth(BUTTON_NEXT_STATE_PREF_WIDTH);
        nextStateButton.setMaxWidth(BUTTON_NEXT_STATE_PREF_WIDTH);
        nextStateButton.setLayoutX(nextStatePane.getWidth() - nextStateButton.getWidth());

        prevStateButton = new Button();
        prevStateButton.setMinWidth(BUTTON_PREV_STATE_PREF_WIDTH);
        prevStateButton.setMaxWidth(BUTTON_PREV_STATE_PREF_WIDTH);
        prevStateButton.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);

        prevStatePane = new Pane();
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

    private void setDisableNextStateControls(boolean disableNextStateControls) {
        for (Region control : nextStateControlsList) {
            control.setDisable(disableNextStateControls);
        }
        nextStateButton.setDisable(disableNextStateControls);
        prevStateButton.setDisable(!disableNextStateControls);
        for (Region control : prevStateControlsList) {
            control.setDisable(!disableNextStateControls);
        }
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
    @Override
    public void updateOnClose() {
        disableAllControls();
    }

    @Override
    public void updateOnOpen() {
        disableAllControls();
    }

    // 2
    @Override
    public void updateOnAuthorizeUser() {
        disableAllControls();
    }

    // 4
    @Override
    public void updateOnSetGameType() {
        disableAllControls();
    }

    // 6
    @Override
    public void updateOnSelectGameMatch() {
        disableAllControls();
    }

    // 7
    @Override
    public void updateOnSetGameMatchIsPlaying() {
        disableAllControls();
    }
}
