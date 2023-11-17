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
        Observer031OnGetGameTypeSet,
        Observer041OnSelectGameType {

    private final MultiGameWebSocketClientManyTimesUse netModel;

    private final Button buttonGetGameTypeSet;
    private final Button buttonForgetGameTypeSet;


    public Pane03GetGameTypeSet(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super();
        this.netModel = multiGameWebSocketClientManyTimesUse;

        buttonGetGameTypeSet = new Button("Get the game type set");
        buttonForgetGameTypeSet = new Button("Forget the game type set");

        updateOnClose();
        getChildren().addAll(buttonGetGameTypeSet, buttonForgetGameTypeSet);

        netModel.addViewOnClose(this);
        netModel.addViewOnLogout(this);
        netModel.addViewOnLogin(this);
        netModel.addViewOnGetGameTypeSet(this);
        netModel.addViewOnSelectGameType(this);

        buttonGetGameTypeSet.setOnAction(event -> {
            disableAllControls();
            netModel.getGameTypeSet();
        });

        buttonForgetGameTypeSet.setOnAction(event -> {
            // disableAllControls();
            // netModel.forgetGameTypeSet();
        });
    }

    private void disableAllControls() {
        buttonGetGameTypeSet.setDisable(true);
        buttonForgetGameTypeSet.setDisable(true);
    }

    @Override
    public void updateOnClose() {
        disableAllControls();
    }

    @Override
    public void updateOnLogout() {
        disableAllControls();
        // comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        // textFieldSelectGameType.setText("");
    }

    @Override
    public void updateOnLogin(ResultOfCredential resultOfCredential) {
        boolean loginDisabled = resultOfCredential == ResultOfCredential.AUTHORISED;
        buttonGetGameTypeSet.setDisable(!loginDisabled);
    }

    @Override
    public void updateOnGetGameTypeSet(ArrayList<Class<? extends ServerBaseModel>> arrayOfServerBaseModel) {
        buttonGetGameTypeSet.setDisable(false);
        buttonForgetGameTypeSet.setDisable(false);
    }

    @Override
    public void updateOnSelectGameType(Class<? extends ServerBaseModel> serverBaseModelClass) {
        System.out.println("serverBaseModelClass = " + serverBaseModelClass);
    }
}