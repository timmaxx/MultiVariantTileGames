package timmax.tilegame.client.statuscontrol;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.websocket.client.*;

import java.util.ArrayList;

public class Pane04SelectGameType extends HBox implements
        Observer010OnClose,
        Observer020OnLogout,
        Observer021OnLogin,
        Observer031OnGetGameTypeSet,
        Observer041OnSelectGameType {

    private final MultiGameWebSocketClientManyTimesUse netModel;

    private final ComboBox<Class<? extends ServerBaseModel>> comboBoxGameTypeSet;
    private final Button buttonSelectGameType;
    private final TextField textFieldSelectGameType;
    private final Button buttonForgetGameType;


    public Pane04SelectGameType(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super();
        this.netModel = multiGameWebSocketClientManyTimesUse;

        comboBoxGameTypeSet = new ComboBox<>();
        buttonSelectGameType = new Button("Select the game type");
        textFieldSelectGameType = new TextField();
        textFieldSelectGameType.setEditable(false);
        buttonForgetGameType = new Button("Forget the game type");

        updateOnClose();
        getChildren().addAll(comboBoxGameTypeSet, buttonSelectGameType, textFieldSelectGameType, buttonForgetGameType);

        netModel.addViewOnClose(this);
        netModel.addViewOnLogout(this);
        netModel.addViewOnLogin(this);
        netModel.addViewOnGetGameTypeSet(this);
        netModel.addViewOnSelectGameType(this);

        comboBoxGameTypeSet.setOnAction(event -> {
            // System.out.println("event = " + event);
        });

        buttonSelectGameType.setOnAction(event -> {
            disableAllControls();
            System.out.println("comboBoxGameTypeSet.getValue() = " + comboBoxGameTypeSet.getValue());
            netModel.gameTypeSelect(comboBoxGameTypeSet.getValue());
        });

        buttonForgetGameType.setOnAction(event -> {
            // netModel.forgetGameTypeSelect();
        });
    }

    private void disableAllControls() {
        comboBoxGameTypeSet.setDisable(true);
        buttonSelectGameType.setDisable(true);
        buttonForgetGameType.setDisable(true);
    }

    @Override
    public void updateOnClose() {
        disableAllControls();
    }

    @Override
    public void updateOnLogout() {
        disableAllControls();
        // comboBoxGameTypeSet.setItems((ObservableList<Class<? extends ServerBaseModel>>) new ArrayList<Class<? extends ServerBaseModel>>());
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        textFieldSelectGameType.setText("");
    }

    @Override
    public void updateOnLogin(ResultOfCredential resultOfCredential) {
//      boolean loginDisabled = resultOfCredential == ResultOfCredential.AUTHORISED;
//      buttonGetGameTypeSet.setDisable(!loginDisabled);
    }

    @Override
    public void updateOnGetGameTypeSet(ArrayList<Class<? extends ServerBaseModel>> arrayOfServerBaseModel) {
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList(arrayOfServerBaseModel));

        comboBoxGameTypeSet.setDisable(false);
        buttonSelectGameType.setDisable(false);
    }

    @Override
    public void updateOnSelectGameType(Class<? extends ServerBaseModel> serverBaseModelClass) {
        System.out.println("serverBaseModelClass = " + serverBaseModelClass);
        textFieldSelectGameType.setText(serverBaseModelClass.getName());
        buttonForgetGameType.setDisable(false);
    }
}