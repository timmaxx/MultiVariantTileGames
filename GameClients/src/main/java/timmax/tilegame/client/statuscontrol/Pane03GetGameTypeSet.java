package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Button;

import timmax.tilegame.basemodel.protocol.TypeOfTransportPackage;
import timmax.tilegame.websocket.client.*;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;

public class Pane03GetGameTypeSet extends AbstractConnectStatePane implements ObserverOnAbstractEvent {
    public Pane03GetGameTypeSet(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse.getClientState());

        Button buttonGetGameTypeSet = new Button("Get the game type set");
        Button buttonForgetGameTypeSet = new Button("Forget the game type set");

        multiGameWebSocketClientManyTimesUse.addViewOnAnyEvent(this);

        buttonGetGameTypeSet.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.getGameTypeSet();
        });

        buttonForgetGameTypeSet.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.forgetGameTypeSet();
        });

        setListsOfControlsAndAllDisable(
                List.of(buttonGetGameTypeSet),
                List.of(buttonForgetGameTypeSet));
    }

    public void updateOnClose() {
        disableAllControls();
    }

    public void updateOnOpen() {
        disableAllControls();
    }

    public void updateOnLogout() {
        disableAllControls();
    }

    public void updateOnLogin() {
        setDisableControlsNextState(false);
    }

    public void updateOnForgetGameTypeSet() {
        setDisableControlsNextState(false);
    }

    public void updateOnGetGameTypeSet() {
        setDisableControlsNextState(true);
    }

    @Override
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
        }
    }
}