package timmax.tilegame.client.statuscontrol;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.websocket.client.*;

public class Pane03GetGameTypeSet extends HBox implements
        Observer010OnClose,
        Observer020OnLogout,
        Observer021OnLogin,
        Observer032OnGetGameTypeSet {

    private final MultiGameWebSocketClientManyTimesUse netModel;

    private final Button buttonGetGameTypeSet;


    public Pane03GetGameTypeSet(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super();
        this.netModel = multiGameWebSocketClientManyTimesUse;

        buttonGetGameTypeSet = new Button("Get the game type set");

        updateOnClose();
        getChildren().addAll(buttonGetGameTypeSet);

        netModel.addViewOnClose(this);
        netModel.addViewOnLogout(this);
        netModel.addViewOnLogin(this);
        netModel.addViewOnGetGameTypeSet(this);

        buttonGetGameTypeSet.setOnAction(event -> {
            disableAllControls();
            netModel.getGameTypeSet();
        });
    }

    private void disableAllControls() {
        buttonGetGameTypeSet.setDisable(true);
    }

    @Override
    public void updateOnClose() {
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
    }

    @Override
    public void updateOnGetGameTypeSet(ArrayList<Class<? extends ServerBaseModel>> arrayOfServerBaseModel) {
        System.out.println("arrayOfServerBaseModel = " + arrayOfServerBaseModel);
        buttonGetGameTypeSet.setDisable(false);
    }
}