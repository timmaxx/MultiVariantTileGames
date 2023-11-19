package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.websocket.client.*;

public class Pane04SelectGameType extends AbstractConnectStatePane {
    private final ComboBox<Class<? extends ServerBaseModel>> comboBoxGameTypeSet;
    private final TextField textFieldSelectGameType;


    public Pane04SelectGameType(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse.getClientState());

        comboBoxGameTypeSet = new ComboBox<>();
        Button buttonSelectGameType = new Button("Select the game type");
        textFieldSelectGameType = new TextField();
        textFieldSelectGameType.setEditable(false);
        Button buttonForgetGameType = new Button("Forget the game type");

        multiGameWebSocketClientManyTimesUse.addCallBackOnIncomingTransportPackageEvent(this);

        comboBoxGameTypeSet.setOnAction(event -> {
            // System.out.println("event = " + event);
        });

        buttonSelectGameType.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.gameTypeSelect(comboBoxGameTypeSet.getValue());
        });

        buttonForgetGameType.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.forgetGameType();
        });

        setListsOfControlsAndAllDisable(
                List.of(comboBoxGameTypeSet, buttonSelectGameType, textFieldSelectGameType),
                List.of(buttonForgetGameType));
    }

    @Override
    public void updateOnClose() {
        disableAllControls();
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        textFieldSelectGameType.setText("");
    }

    @Override
    public void updateOnOpen() {
        disableAllControls();
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        textFieldSelectGameType.setText("");
    }

    @Override
    public void updateOnLogout() {
        disableAllControls();
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        textFieldSelectGameType.setText("");
    }

    @Override
    public void updateOnLogin() {
        disableAllControls();
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        textFieldSelectGameType.setText("");
    }

    @Override
    public void updateOnForgetGameTypeSet() {
        disableAllControls();
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        textFieldSelectGameType.setText("");
    }

    @Override
    public void updateOnGetGameTypeSet() {
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList(clientState.getArrayListOfServerBaseModelClass()));
        textFieldSelectGameType.setText("");
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnForgetGameType() {
        textFieldSelectGameType.setText("");
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnSelectGameType() {
        textFieldSelectGameType.setText(clientState.getServerBaseModelClass().getName());
        setDisableControlsNextState(true);
    }
}