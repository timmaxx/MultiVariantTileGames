package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.basemodel.protocol.TypeOfTransportPackage;
import timmax.tilegame.websocket.client.*;

import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.*;
import static timmax.tilegame.basemodel.protocol.TypeOfTransportPackage.GET_GAME_TYPE_SET;

public class Pane04SelectGameType extends AbstractConnectStatePane implements ObserverOnAbstractEvent {
    private final ComboBox<Class<? extends ServerBaseModel>> comboBoxGameTypeSet;
    private final TextField textFieldSelectGameType;


    public Pane04SelectGameType(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse.getClientState());

        comboBoxGameTypeSet = new ComboBox<>();
        Button buttonSelectGameType = new Button("Select the game type");
        textFieldSelectGameType = new TextField();
        textFieldSelectGameType.setEditable(false);
        Button buttonForgetGameType = new Button("Forget the game type");

        multiGameWebSocketClientManyTimesUse.addViewOnAnyEvent(this);

        comboBoxGameTypeSet.setOnAction(event -> {
            // System.out.println("event = " + event);
        });

        buttonSelectGameType.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.gameTypeSelect(comboBoxGameTypeSet.getValue());
        });

        buttonForgetGameType.setOnAction(event -> {
            // netModel.forgetGameTypeSelect();
        });

        setListsOfControlsAndAllDisable(
                List.of(comboBoxGameTypeSet, buttonSelectGameType, textFieldSelectGameType),
                List.of(buttonForgetGameType));
    }

    public void updateOnClose() {
        disableAllControls();
        Platform.runLater(() -> {
            comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
            textFieldSelectGameType.setText("");
        });
    }

    public void updateOnOpen() {
        disableAllControls();
        Platform.runLater(() -> {
            comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
            textFieldSelectGameType.setText("");
        });
    }

    public void updateOnLogout() {
        disableAllControls();
        Platform.runLater(() -> {
            comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
            textFieldSelectGameType.setText("");
        });
    }

    public void updateOnLogin() {
        disableAllControls();
        Platform.runLater(() -> {
            comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
            textFieldSelectGameType.setText("");
        });
    }

    public void updateOnForgetGameTypeSet() {
        disableAllControls();
        //  Если ранее comboBoxGameTypeSet уже было заполнено (т.е. вызывался updateOnGetGameTypeSet)
        //  и не использовать здесь Platform.runLater(), то возникнет исключение:
        //  Not on FX application thread
        //  Например:
        //  Exception in thread "WebSocketConnectReadThread-25" java.lang.IllegalStateException: Not on FX application thread; currentThread = WebSocketConnectReadThread-25
        Platform.runLater(() -> {
            comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
            textFieldSelectGameType.setText("");
        });
    }

    public void updateOnGetGameTypeSet() {
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList(clientState.getArrayListOfServerBaseModelClass()));
        textFieldSelectGameType.setText("");
        setDisableControlsNextState(false);
    }

    public void updateOnSelectGameType() {
        textFieldSelectGameType.setText(clientState.getServerBaseModelClass().getName());
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
        }/*else if (typeOfTransportPackage == ...) {
            updateOn...();
        }*/else if (typeOfTransportPackage == SELECT_GAME_TYPE) {
            updateOnSelectGameType();
        }
    }
}