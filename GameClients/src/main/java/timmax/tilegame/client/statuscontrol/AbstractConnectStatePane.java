package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Control;
import javafx.scene.layout.HBox;

import timmax.tilegame.basemodel.protocol.ClientState;
import timmax.tilegame.basemodel.protocol.TypeOfTransportPackage;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public abstract class AbstractConnectStatePane extends HBox {
    private List<Control> listOfControlsNextState;
    private List<Control> listOfControlsPrevState;
    protected ClientState clientState;

    public AbstractConnectStatePane(ClientState clientState) {
        this.clientState = clientState;
    }

    public void setListsOfControlsAndAllDisable(List<Control> listOfControlsNextState, List<Control> listOfControlsPrevState) {
        this.listOfControlsNextState = listOfControlsNextState;
        this.listOfControlsPrevState = listOfControlsPrevState;
        getChildren().clear();
        getChildren().addAll(listOfControlsNextState);
        getChildren().addAll(listOfControlsPrevState);
        disableAllControls();
    }

    protected void disableAllControls() {
        for (Control control: listOfControlsNextState) {
            control.setDisable(true);
        }
        for (Control control: listOfControlsPrevState) {
            control.setDisable(true);
        }
    }

    protected void setDisableControlsNextState(boolean disableControlsNextState) {
        for (Control control: listOfControlsNextState) {
            control.setDisable(disableControlsNextState);
        }
        for (Control control: listOfControlsPrevState) {
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

    public void updateOnSelectGameType() {
    }

    public void update(TypeOfTransportPackage typeOfTransportPackage) {
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
        }/*else if (typeOfTransportPackage == ...) {
            updateOn...();
        }*/else if (typeOfTransportPackage == SELECT_GAME_TYPE) {
            updateOnSelectGameType();
        }
    }
}