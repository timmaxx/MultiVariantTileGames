package timmax.tilegame.client.statuscontrol;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.websocket.client.*;

public class Pane03GetGameTypeSet extends HBox implements
        Observer010OnClose,
        Observer020OnLogout,
        Observer021OnLogin,
        Observer032OnGetGameTypeSet,
        Observer033OnSelectGameType {

    private final MultiGameWebSocketClientManyTimesUse netModel;

    private final Button buttonGetGameTypeSet;
    private final Button buttonForgetGameTypeSet;
    private final ComboBox<Class<? extends ServerBaseModel>> comboBoxGameTypeSet;
    private final Button buttonSelectGameType;
    private final TextField textFieldSelectGameType;
    private final Button buttonForgetGameType;


    public Pane03GetGameTypeSet(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super();
        this.netModel = multiGameWebSocketClientManyTimesUse;

        buttonGetGameTypeSet = new Button("Get the game type set");
        buttonForgetGameTypeSet = new Button("Forget the game type set");
        comboBoxGameTypeSet = new ComboBox<>();
        buttonSelectGameType = new Button("Select the game type");
        textFieldSelectGameType = new TextField();
        textFieldSelectGameType.setEditable(false);
        buttonForgetGameType = new Button("Forget the game type");

        updateOnClose();
        getChildren().addAll(buttonGetGameTypeSet, buttonForgetGameTypeSet, comboBoxGameTypeSet, buttonSelectGameType, textFieldSelectGameType, buttonForgetGameType);

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
        buttonGetGameTypeSet.setDisable(true);
        buttonForgetGameTypeSet.setDisable(true);
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
        boolean loginDisabled = resultOfCredential == ResultOfCredential.AUTHORISED;
        buttonGetGameTypeSet.setDisable(!loginDisabled);
    }

    @Override
    public void updateOnGetGameTypeSet(ArrayList<Class<? extends ServerBaseModel>> arrayOfServerBaseModel) {
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList(arrayOfServerBaseModel));

        buttonGetGameTypeSet.setDisable(false);
        buttonForgetGameTypeSet.setDisable(false);
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