package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Control;
import javafx.scene.layout.HBox;

public abstract class AbstractConnectStatePane extends HBox {
    private List<Control> listOfControlsNextState;
    private List<Control> listOfControlsPrevState;


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
}