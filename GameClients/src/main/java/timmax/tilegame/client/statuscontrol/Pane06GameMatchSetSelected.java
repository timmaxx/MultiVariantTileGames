package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import timmax.tilegame.basemodel.protocol.server_client.GameMatchId;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.*;

public class Pane06GameMatchSetSelected extends AbstractConnectStatePane {
    private final ComboBox<String> gameSetComboBox;

    public Pane06GameMatchSetSelected(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        gameSetComboBox = new ComboBox<>();

        nextStateButton.setText("Select the game match");
        nextStateButton.setOnAction(event -> {
            disableAllControls();
            transportOfClient.setGameMatch(new GameMatchId(gameSetComboBox.getValue()));
        });

        // Контролы для продвижения состояния "назад":
        prevStateButton.setText("Forget the game match");
        prevStateButton.setFocusTraversable(false);
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            transportOfClient.forgetGameMatch();
        });

        // 1
        gameSetComboBox.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        gameSetComboBox.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);

        // Получилась 1 строка контролов:
        nextStatePane.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * 1);
        nextStatePane.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * 1);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(gameSetComboBox),
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
    public void updateOnSetUser() {
        doOnPrevState();
    }

    // 4
    @Override
    public void updateOnSetGameType() {
        ObservableList<GameMatchId> observableList = FXCollections.observableArrayList();
        observableList.addAll(transportOfClient.getLocalClientStateAutomaton().getGameMatchXSet());
        gameSetComboBox.setItems(
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
        gameSetComboBox.setItems(FXCollections.observableArrayList());
    }
}
