package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.ServerBaseModel;
import timmax.tilegame.websocket.client.*;

public class Pane04SelectGameType extends AbstractConnectStatePane implements
        Observer010OnClose,
        Observer011OnOpen,
        Observer020OnLogout,
        Observer021OnLogin,
        Observer030OnForgetGameTypeSet,
        Observer031OnGetGameTypeSet,
        Observer041OnSelectGameType {

    private final ComboBox<Class<? extends ServerBaseModel>> comboBoxGameTypeSet;
    private final TextField textFieldSelectGameType;


    public Pane04SelectGameType(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse.getClientState());

        comboBoxGameTypeSet = new ComboBox<>();
        Button buttonSelectGameType = new Button("Select the game type");
        textFieldSelectGameType = new TextField();
        textFieldSelectGameType.setEditable(false);
        Button buttonForgetGameType = new Button("Forget the game type");

        multiGameWebSocketClientManyTimesUse.addViewOnClose(this);
        multiGameWebSocketClientManyTimesUse.addViewOnOpen(this);
        multiGameWebSocketClientManyTimesUse.addViewOnLogout(this);
        multiGameWebSocketClientManyTimesUse.addViewOnLogin(this);
        multiGameWebSocketClientManyTimesUse.addViewOnForgetGameTypeSet(this);
        multiGameWebSocketClientManyTimesUse.addViewOnGetGameTypeSet(this);
        multiGameWebSocketClientManyTimesUse.addViewOnSelectGameType(this);

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

    @Override
    public void updateOnClose() {
        disableAllControls();
        Platform.runLater(() -> {
            comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
            textFieldSelectGameType.setText("");
        });
    }

    @Override
    public void updateOnOpen() {
        disableAllControls();
        Platform.runLater(() -> {
            comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
            textFieldSelectGameType.setText("");
        });
    }

    @Override
    public void updateOnLogout() {
        disableAllControls();
        Platform.runLater(() -> {
            comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
            textFieldSelectGameType.setText("");
        });
    }

    @Override
    public void updateOnLogin() {
        disableAllControls();
        Platform.runLater(() -> {
            comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
            textFieldSelectGameType.setText("");
        });
    }

    @Override
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

    @Override
    public void updateOnGetGameTypeSet() {
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList(clientState.getArrayListOfServerBaseModelClass()));
        textFieldSelectGameType.setText("");
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnSelectGameType() {
        textFieldSelectGameType.setText(clientState.getServerBaseModelClass().getName());
        setDisableControlsNextState(true);
    }
}