package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Button;

import timmax.tilegame.websocket.client.*;

public class Pane03GetGameTypeSet extends AbstractConnectStatePane implements
        Observer010OnClose,
        Observer011OnOpen,
        Observer020OnLogout,
        Observer021OnLogin,
        Observer030OnForgetGameTypeSet,
        Observer031OnGetGameTypeSet {


    public Pane03GetGameTypeSet(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse.getClientState());

        Button buttonGetGameTypeSet = new Button("Get the game type set");
        Button buttonForgetGameTypeSet = new Button("Forget the game type set");

        multiGameWebSocketClientManyTimesUse.addViewOnClose(this);
        multiGameWebSocketClientManyTimesUse.addViewOnOpen(this);
        multiGameWebSocketClientManyTimesUse.addViewOnLogout(this);
        multiGameWebSocketClientManyTimesUse.addViewOnLogin(this);
        multiGameWebSocketClientManyTimesUse.addViewOnForgetGameTypeSet(this);
        multiGameWebSocketClientManyTimesUse.addViewOnGetGameTypeSet(this);

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

    @Override
    public void updateOnClose() {
        disableAllControls();
    }

    @Override
    public void updateOnOpen() {
        disableAllControls();
    }

    @Override
    public void updateOnLogout() {
        disableAllControls();
    }

    @Override
    public void updateOnLogin() {
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnForgetGameTypeSet() {
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnGetGameTypeSet() {
        setDisableControlsNextState(true);
    }
}