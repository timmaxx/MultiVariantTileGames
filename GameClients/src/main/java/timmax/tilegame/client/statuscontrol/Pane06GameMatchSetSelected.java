package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.*;

public class Pane06GameMatchSetSelected extends AbstractConnectStatePane {
    private final ComboBox<String> gameMatchSetComboBox;

    public Pane06GameMatchSetSelected(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        gameMatchSetComboBox = new ComboBox<>();

        nextStateButton.setText("Select the game match");
        nextStateButton.setOnAction(event -> {
            if (gameMatchSetComboBox.getValue() == null || gameMatchSetComboBox.getValue().isEmpty()) {
                return;
            }
            disableAllControls();
            transportOfClient.setGameMatch(new GameMatchId(gameMatchSetComboBox.getValue()));
        });

        // Контролы для продвижения состояния "назад":
        prevStateButton.setText("Forget the game match");
        prevStateButton.setFocusTraversable(false);
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            transportOfClient.resetGameType();
        });

        // 1
        gameMatchSetComboBox.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        gameMatchSetComboBox.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);

        // Получилась 1 строка контролов:
        nextStatePane.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * 1);
        nextStatePane.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * 1);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(gameMatchSetComboBox),
                List.of()
        );
    }

    // interface ObserverOnAbstractEvent
    // 4
    @Override
    public void updateOnSetGameType() {
        ObservableList<GameMatchId> observableList = FXCollections.observableArrayList();
        observableList.addAll(transportOfClient.getLocalClientStateAutomaton().getGameMatchXSet());
        gameMatchSetComboBox.setItems(
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
    public void updateOnSetGameMatch() {
        doOnNextState();
    }

    // doOnХХХState()
    @Override
    protected void doOnPrevState() {
        super.doOnPrevState();
        gameMatchSetComboBox.setItems(FXCollections.observableArrayList());
    }
}
