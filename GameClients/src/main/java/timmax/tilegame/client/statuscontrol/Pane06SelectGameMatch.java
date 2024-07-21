package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.*;

// ToDo: Переименовать класс.
// Панель соответствует состоянию 06GameMatchSetSelected
public class Pane06SelectGameMatch extends AbstractConnectStatePane {
    private final ComboBox<String> comboBoxGameSet;

    public Pane06SelectGameMatch(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        comboBoxGameSet = new ComboBox<>();

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

        // Получилась 1 строка контролов:
        paneNextState.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * 1);
        paneNextState.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * 1);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(comboBoxGameSet),
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
    public void updateOnForgetGameMatchSet() {
        doOnPrevState();
    }

    @Override
    public void updateOnSetGameType() {
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
    }
}
