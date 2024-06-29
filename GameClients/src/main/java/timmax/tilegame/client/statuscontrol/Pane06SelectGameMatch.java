package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.protocol.server_client.InstanceIdOfModel;
import timmax.tilegame.transport.TransportOfClient;

public class Pane06SelectGameMatch<ClientId> extends AbstractConnectStatePane<ClientId> {
    private final ComboBox<String> comboBoxGameSet;
    private final TextField textFieldSelectedGame;

    public Pane06SelectGameMatch(TransportOfClient<ClientId> transportOfClient) {
        super(transportOfClient);

        // Контролы для продвижения состояния "вперёд":
        comboBoxGameSet = new ComboBox<>();
        textFieldSelectedGame = new TextField();
        textFieldSelectedGame.setEditable(false);

        buttonNextState.setText("Select the game match");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            transportOfClient.gameMatchSelect(new InstanceIdOfModel(comboBoxGameSet.getValue()));
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Forget the game match");
        buttonPrevState.setFocusTraversable(false);
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            transportOfClient.forgetGameMatch();
        });

        comboBoxGameSet.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        comboBoxGameSet.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);

        textFieldSelectedGame.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        textFieldSelectedGame.setLayoutY(DIFFERENCE_OF_LAYOUT_Y);

        paneNextState.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * 2);
        paneNextState.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * 2);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(comboBoxGameSet, textFieldSelectedGame),
                List.of()
        );
    }

    // Implemented methods of interface ObserverOnAbstractEvent
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
    public void updateOnForgetUser() {
        doOnPrevState();
    }

    @Override
    public void updateOnSetUser() {
        doOnPrevState();
    }

    // 3
    @Override
    public void updateOnForgetGameTypeSet() {
        doOnPrevState();
    }

    @Override
    public void updateOnSetGameTypeSet() {
        doOnPrevState();
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
        doOnPrevState();
    }

    @Override
    public void updateOnSetGameType() {
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
        observableList.addAll(transportOfClient.getLocalClientStateAutomaton().getGameMatchSet());
        comboBoxGameSet.setItems(
                FXCollections.observableArrayList(
                        observableList
                                .stream()
                                .map(InstanceIdOfModel::getId)
                                .toList()
                )
        );
        doOnThisState();
    }

    // 6
    @Override
    public void updateOnForgetGameMatch() {
        doOnThisState();
    }

    @Override
    public void updateOnSetGameMatch() {
        doOnNextState();
    }

    // doOnХХХState()
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
        textFieldSelectedGame.setText(transportOfClient.getLocalClientStateAutomaton().getServerBaseModel().toString());
        super.doOnNextState();
    }
}
