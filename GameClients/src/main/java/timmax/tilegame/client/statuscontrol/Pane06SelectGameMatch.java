package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.*;

public class Pane06SelectGameMatch extends AbstractConnectStatePane {
    private final ComboBox<String> comboBoxGameSet;
    private final TextField textFieldSelectedGame;

    public Pane06SelectGameMatch(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        comboBoxGameSet = new ComboBox<>();
        textFieldSelectedGame = new TextField();
        textFieldSelectedGame.setEditable(false);

        buttonNextState.setText("Select the game match");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            transportOfClient.setGameMatch(new GameMatchId(comboBoxGameSet.getValue()));
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Forget the game match");
        buttonPrevState.setFocusTraversable(false);
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            transportOfClient.forgetGameMatch();
        });

        // 1
        comboBoxGameSet.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        comboBoxGameSet.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);

        // 2
        textFieldSelectedGame.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        textFieldSelectedGame.setLayoutY(DIFFERENCE_OF_LAYOUT_Y);

        // Получилось 3 строки контролов:
        paneNextState.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * 2);
        paneNextState.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * 2);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(comboBoxGameSet, textFieldSelectedGame),
                List.of()
        );
    }

    // interface ObserverOnAbstractEvent
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
    public void updateOnForgetGameTypeSet() {
        doOnPrevState();
    }

    @Override
    public void updateOnSetUser() {
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
    public void updateOnSetGameMatchSet() {
        ObservableList<GameMatchId> observableList = FXCollections.observableArrayList();
        observableList.addAll(transportOfClient.getLocalClientStateAutomaton().getGameMatchXSet());
        comboBoxGameSet.setItems(
                FXCollections.observableArrayList(
                        observableList
                                .stream()
                                .map(GameMatchId::getId)
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
        textFieldSelectedGame.setText(transportOfClient.getLocalClientStateAutomaton().getGameMatchX().toString());
        super.doOnNextState();
    }
}
