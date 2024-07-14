package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.*;

public class Pane04SelectGameType extends AbstractConnectStatePane {
    private final ComboBox<String> comboBoxGameTypeSet;
    private final TextField textFieldSelectedGameType;

    public Pane04SelectGameType(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        comboBoxGameTypeSet = new ComboBox<>();
        textFieldSelectedGameType = new TextField();
        textFieldSelectedGameType.setEditable(false);

        buttonNextState.setText("Select the game type");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            String gameName = comboBoxGameTypeSet.getValue();

            GameType gameType =
                    transportOfClient
                            .getLocalClientStateAutomaton()
                            .getGameTypeSet()
                            .stream()
                            .filter(x -> x.getGameName().equals(gameName))
                            .findAny()
                            .orElse(null);

            transportOfClient.setGameType(gameType);
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Forget the game type");
        buttonPrevState.setFocusTraversable(false);
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            transportOfClient.forgetGameType();
        });

        // 1
        comboBoxGameTypeSet.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        comboBoxGameTypeSet.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);

        // 2
        textFieldSelectedGameType.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        textFieldSelectedGameType.setLayoutY(DIFFERENCE_OF_LAYOUT_Y);

        // Получилось 2 строки контролов:
        paneNextState.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * 2);
        paneNextState.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * 2);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(comboBoxGameTypeSet, textFieldSelectedGameType),
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
        comboBoxGameTypeSet.setItems(
                FXCollections.observableArrayList(
                        transportOfClient
                                .getLocalClientStateAutomaton()
                                .getGameTypeSet()
                                .stream()
                                .map(GameType::getGameName)
                                .toList()
                )
        );
        doOnThisState();
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
        doOnThisState();
    }

    @Override
    public void updateOnSetGameType() {
        doOnNextState();
    }

    // doOnХХХState()
    @Override
    protected void doOnPrevState() {
        super.doOnPrevState();
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGameType.setText("");
    }

    @Override
    protected void doOnThisState() {
        textFieldSelectedGameType.setText("");
        super.doOnThisState();
    }

    @Override
    protected void doOnNextState() {
        textFieldSelectedGameType.setText(transportOfClient.getLocalClientStateAutomaton().getGameType().getGameName());
        super.doOnNextState();
    }
}
