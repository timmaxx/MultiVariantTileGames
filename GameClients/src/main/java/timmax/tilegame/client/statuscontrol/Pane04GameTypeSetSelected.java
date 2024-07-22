package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.*;

public class Pane04GameTypeSetSelected extends AbstractConnectStatePane {
    private final ComboBox<String> gameTypeSetComboBox;

    public Pane04GameTypeSetSelected(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        gameTypeSetComboBox = new ComboBox<>();

        nextStateButton.setText("Select the game type");
        nextStateButton.setOnAction(event -> {
            disableAllControls();
            String gameTypeName = gameTypeSetComboBox.getValue();

            GameType gameType =
                    transportOfClient
                            .getLocalClientStateAutomaton()
                            .getGameTypeSet()
                            .stream()
                            .filter(x -> x.getGameTypeName().equals(gameTypeName))
                            .findAny()
                            .orElse(null);

            transportOfClient.setGameType(gameType);
        });

        // Контролы для продвижения состояния "назад":
        prevStateButton.setText("Forget the game type");
        prevStateButton.setFocusTraversable(false);
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            transportOfClient.forgetGameType();
        });

        // 1
        gameTypeSetComboBox.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        gameTypeSetComboBox.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);

        // Получилось 1 строка контролов:
        nextStatePane.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * 1);
        nextStatePane.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * 1);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(gameTypeSetComboBox),
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
        gameTypeSetComboBox.setItems(
                FXCollections.observableArrayList(
                        transportOfClient
                                .getLocalClientStateAutomaton()
                                .getGameTypeSet()
                                .stream()
                                .map(GameType::getGameTypeName)
                                .toList()
                )
        );
        doOnThisState();
    }

    // 4
    @Override
    public void updateOnSetGameType() {
        doOnNextState();
    }

    // doOnХХХState()
    @Override
    protected void doOnPrevState() {
        super.doOnPrevState();
        gameTypeSetComboBox.setItems(FXCollections.observableArrayList());
    }
}