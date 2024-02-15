package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane06SelectGameMatch extends AbstractConnectStatePane {
    private final ComboBox<String> comboBoxGameSet;
    private final TextField textFieldSelectedGame;

    public Pane06SelectGameMatch(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
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
        doOnPrevState();
    }

    @Override
    public void updateOnOpen() {
        doOnPrevState();
    }

    // 2
    @Override
    public void updateOnLogout() {
        doOnPrevState();
    }

    @Override
    public void updateOnLogin() {
        doOnPrevState();
    }

    // 3
    @Override
    public void updateOnForgetGameTypeSet() {
        doOnPrevState();
    }

    @Override
    public void updateOnGetGameTypeSet() {
        doOnPrevState();
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
        doOnPrevState();
    }

    @Override
    public void updateOnSelectGameType() {
        doOnPrevState();
    }

    // 5
    @Override
    public void updateOnForgetGameMatchSet() {
        doOnPrevState();
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
        doOnThisState();
    }

    // 6
    @Override
    public void updateOnForgetGameMatch() {
        doOnThisState();
    }

    @Override
    public void updateOnSelectGameMatch() {
        doOnNextState();
    }

    //
    @Override
    protected void doOnPrevState() {
        super.doOnPrevState();
        comboBoxGameSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGame.setText("");
    }

    @Override
    protected void doOnThisState() {
        // ToDo: Эти строки всегда совпадают с последними строками предыдущего метода для всех классов Pane0x.
        //       Ввести дополнительный приватный метод и вызывать его.
        textFieldSelectedGame.setText("");
        super.doOnThisState();
    }

    @Override
    protected void doOnNextState() {
        textFieldSelectedGame.setText(localClientState.getServerBaseModel().toString());
        setDisableControlsNextState(true);
    }
}
