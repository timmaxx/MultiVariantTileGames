package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane06SelectGame extends AbstractConnectStatePane {
    private final ComboBox<String> comboBoxGameSet;
    private final TextField textFieldSelectedGame;

    public Pane06SelectGame(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        // Контролы для продвижения состояния "вперёд":
        comboBoxGameSet = new ComboBox<>();
        buttonNextState.setText("Select the game match");
        textFieldSelectedGame = new TextField();
        textFieldSelectedGame.setEditable(false);

        buttonNextState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.gameMatchSelect(new InstanceIdOfModel(comboBoxGameSet.getValue()));
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Forget the game match");
        buttonPrevState.setFocusTraversable(false);

        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.forgetGameMatch();
        });

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(comboBoxGameSet, buttonNextState, textFieldSelectedGame),
                List.of(buttonPrevState)
        );
    }

    // 1
    @Override
    public void updateOnClose() {
        disableAllControls();
        comboBoxGameSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGame.setText("");
    }

    @Override
    public void updateOnOpen() {
        disableAllControls();
        comboBoxGameSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGame.setText("");
    }

    // 2
    @Override
    public void updateOnLogout() {
        disableAllControls();
        comboBoxGameSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGame.setText("");
    }

    @Override
    public void updateOnLogin() {
        disableAllControls();
        comboBoxGameSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGame.setText("");
    }

    // 3
    @Override
    public void updateOnForgetGameTypeSet() {
        disableAllControls();
        comboBoxGameSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGame.setText("");
    }

    @Override
    public void updateOnGetGameTypeSet() {
        disableAllControls();
        comboBoxGameSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGame.setText("");
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
        disableAllControls();
        comboBoxGameSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGame.setText("");
    }

    @Override
    public void updateOnSelectGameType() {
        disableAllControls();
        comboBoxGameSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGame.setText("");
    }

    // 5
    @Override
    public void updateOnForgetGameMatchSet() {
        disableAllControls();
        comboBoxGameSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGame.setText("");
    }

    @Override
    public void updateOnGetGameMatchSet() {
        // Также см. комментарии к EventOfClient51GiveGamePlaySet
        ObservableList<InstanceIdOfModel> observableList = FXCollections.observableArrayList(new InstanceIdOfModel("New game"));
        observableList.addAll(localClientState.getGameMatchSet());
        comboBoxGameSet.setItems(FXCollections.observableArrayList(
                observableList
                        .stream()
                        .map(InstanceIdOfModel::getId)
                        .toList()
        ));
        textFieldSelectedGame.setText("");
        setDisableControlsNextState(false);
    }

    // 6
    @Override
    public void updateOnForgetGameMatch() {
        // ToDo: Эти строки всегда совпадают с последними строками предыдущего метода для всех классов Pane0x.
        //       Ввести дополнительный приватный метод и вызывать его.
        textFieldSelectedGame.setText("");
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnSelectGameMatch() {
        textFieldSelectedGame.setText(localClientState.getServerBaseModel().toString());
        setDisableControlsNextState(true);
    }
}
