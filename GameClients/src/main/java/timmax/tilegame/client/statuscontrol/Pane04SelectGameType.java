package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane04SelectGameType extends AbstractConnectStatePane {
    private final ComboBox<String> comboBoxGameTypeSet;
    private final TextField textFieldSelectedGameType;

    public Pane04SelectGameType(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        // Контролы для продвижения состояния "вперёд":
        comboBoxGameTypeSet = new ComboBox<>();
        buttonNextState.setText("Select the game type");
        textFieldSelectedGameType = new TextField();
        textFieldSelectedGameType.setEditable(false);

        buttonNextState.setOnAction(event -> {
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

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Forget the game type");
        buttonPrevState.setFocusTraversable(false);

        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.forgetGameType();
        });

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(comboBoxGameTypeSet, buttonNextState, textFieldSelectedGameType),
                List.of(buttonPrevState)
        );
    }

    // 1
    @Override
    public void updateOnClose() {
        disableAllControls();
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGameType.setText("");
    }

    @Override
    public void updateOnOpen() {
        disableAllControls();
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGameType.setText("");
    }

    // 2
    @Override
    public void updateOnLogout() {
        disableAllControls();
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGameType.setText("");
    }

    @Override
    public void updateOnLogin() {
        disableAllControls();
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGameType.setText("");
    }

    // 3
    @Override
    public void updateOnForgetGameTypeSet() {
        disableAllControls();
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList());
        textFieldSelectedGameType.setText("");
    }

    @Override
    public void updateOnGetGameTypeSet() {
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList(
                localClientState.getGameTypeSet()
                        .stream()
                        .map(ModelOfServerDescriptor::getGameName)
                        .toList()
        ));
        textFieldSelectedGameType.setText("");
        setDisableControlsNextState(false);
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
        textFieldSelectedGameType.setText("");
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnSelectGameType() {
        textFieldSelectedGameType.setText(localClientState.getGameType().getGameName());
        setDisableControlsNextState(true);
    }
}
