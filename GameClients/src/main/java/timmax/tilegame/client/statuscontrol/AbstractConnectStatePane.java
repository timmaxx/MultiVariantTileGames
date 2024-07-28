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
    public void updateOnAuthorizeUser() {
        doOnPrevState();
    }

    // 4
    @Override
    public void updateOnSetGameType() {
        doOnPrevState();
    }

    // 6
    @Override
    public void updateOnSetGameMatch() {
        doOnPrevState();
    }

    // 7
    @Override
    public void updateOnSetGameMatchIsPlaying() {
        doOnPrevState();
    }
}
