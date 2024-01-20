package timmax.tilegame.client.statuscontrol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

import java.util.List;

public class Pane06SelectGame extends AbstractConnectStatePane {
    private final ComboBox<String> comboBoxGameSet;
    private final TextField textFieldSelectedGame;

    public Pane06SelectGame(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        comboBoxGameSet = new ComboBox<>();
        Button buttonSelectGame = new Button("Select the game");
        textFieldSelectedGame = new TextField();
        textFieldSelectedGame.setEditable(false);

        Button buttonForgetGame = new Button("Forget the game");
        buttonForgetGame.setFocusTraversable(false);

        buttonSelectGame.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.gamePlaySelect(new InstanceIdOfModel(comboBoxGameSet.getValue()));
        });

/*
        buttonSelectGameType.setOnAction(event -> {
            disableAllControls();
            String gameName = comboBoxGameTypeSet.getValue();
            ModelOfServerDescriptor modelOfServerDescriptor = multiGameWebSocketClientManyTimesUse
                    .getLocalClientState()
                    .getGameTypeSet()
                    .stream()
                    .filter(x -> x.getGameName().equals(gameName))
                    .findAny()
                    .orElse(null);
            multiGameWebSocketClientManyTimesUse.gameTypeSelect(modelOfServerDescriptor);
        });
*/

        buttonForgetGame.setOnAction(event -> disableAllControls());

        setListsOfControlsAndAllDisable(
                List.of(comboBoxGameSet, buttonSelectGame, /*buttonNewGame, */textFieldSelectedGame),
                List.of(buttonForgetGame)
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
    public void updateOnForgetGamePlaySet() {
        disableAllControls();
        comboBoxGameSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGame.setText("");
    }

    @Override
    public void updateOnGetGamePlaySet() {
        // Также см. комментарии к EventOfClient51GetGamePlaySet
        ObservableList<InstanceIdOfModel> observableList = FXCollections.observableArrayList(new InstanceIdOfModel("New game"));
        observableList.addAll(localClientState.getGamePlaySet());
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
/*
    @Override
    public void updateOnForgetGameMatch() {
        textFieldSelectedGame.setText("");
        setDisableControlsNextState(false);
    }
*/
    @Override
    public void updateOnSelectGameMatch() {
        textFieldSelectedGame.setText(localClientState.getServerBaseModel().toString());
        setDisableControlsNextState(true);
    }
}
