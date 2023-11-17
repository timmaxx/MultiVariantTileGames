package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Button;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.websocket.client.*;

public class Pane03GetGameTypeSet extends AbstractConnectStatePane implements
        Observer010OnClose,
        Observer011OnOpen,
        Observer020OnLogout,
        Observer021OnLogin,
        Observer030OnForgetGameTypeSet,
        Observer031OnGetGameTypeSet {

    private final Button buttonGetGameTypeSet;
    private final Button buttonForgetGameTypeSet;


    public Pane03GetGameTypeSet(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        buttonGetGameTypeSet = new Button("Get the game type set");
        buttonForgetGameTypeSet = new Button("Forget the game type set");

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
    public void updateOnLogin(ResultOfCredential resultOfCredential) {
        boolean loginDisabled = resultOfCredential == ResultOfCredential.AUTHORISED;
        buttonGetGameTypeSet.setDisable(!loginDisabled);
        buttonForgetGameTypeSet.setDisable(true);
    }

    @Override
    public void updateOnForgetGameTypeSet() {
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnGetGameTypeSet(List<Class<? extends ServerBaseModel>> arrayOfServerBaseModel) {
        setDisableControlsNextState(true);
    }
}