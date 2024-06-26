package timmax.tilegame.client.statuscontrol;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.protocol.server.ModelOfServerDescriptor;
import timmax.tilegame.transport.TransportOfClient;

public class Pane04SelectGameType<ClientId> extends AbstractConnectStatePane<ClientId> {
    private final ComboBox<String> comboBoxGameTypeSet;
    private final TextField textFieldSelectedGameType;

    public Pane04SelectGameType(TransportOfClient<ClientId> transportOfClient) {
        super(transportOfClient);

        // Контролы для продвижения состояния "вперёд":
        comboBoxGameTypeSet = new ComboBox<>();
        textFieldSelectedGameType = new TextField();
        textFieldSelectedGameType.setEditable(false);

        buttonNextState.setText("Select the game type");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            String gameName = comboBoxGameTypeSet.getValue();

            //  ToDo: Код в следующем блоке предпочтительнее, но он не компилируется
            //        (перестал после замены класса LocalClientState на ClientStateAutomaton)...
            ModelOfServerDescriptor modelOfServerDescriptor = null;
            for (int i = 0; i < transportOfClient.getLocalClientState().getGameTypeSet().size(); i++) {
                if (((ModelOfServerDescriptor)(transportOfClient.getLocalClientState().getGameTypeSet().stream().toList().get(i))).getGameName().equals(gameName)) {
                    modelOfServerDescriptor = ((ModelOfServerDescriptor)(transportOfClient.getLocalClientState().getGameTypeSet().stream().toList().get(i)));
                    break;
                }
            }
//

/*
            ModelOfServerDescriptor modelOfServerDescriptor =
                    iModelOfClient
                    .getLocalClientState()
                    .getGameTypeSet()
                    .stream()
                    .filter(x -> x.getGameName().equals(gameName))
                    .findAny()
                    .orElse(null);
*/

            transportOfClient.gameTypeSelect(modelOfServerDescriptor);
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Forget the game type");
        buttonPrevState.setFocusTraversable(false);
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            transportOfClient.forgetGameType();
        });

        comboBoxGameTypeSet.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        comboBoxGameTypeSet.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);

        textFieldSelectedGameType.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        textFieldSelectedGameType.setLayoutY(DIFFERENCE_OF_LAYOUT_Y);

        paneNextState.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * 2);
        paneNextState.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * 2);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(comboBoxGameTypeSet, textFieldSelectedGameType),
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
//  ToDo: Код в следующем блоке предпочтительнее, но он не компилируется
//        (перестал после замены класса LocalClientState на ClientStateAutomaton)...
        List<String> tmp2 = new ArrayList<>();
        for (int i = 0; i < transportOfClient.getLocalClientState().getGameTypeSet().size(); i++) {
            ModelOfServerDescriptor tmpI = (ModelOfServerDescriptor)(transportOfClient.getLocalClientState().getGameTypeSet().stream().toList().get(i));
            tmp2.add(tmpI.getGameName());
        }
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList(tmp2));
//

/*
        comboBoxGameTypeSet.setItems(FXCollections.observableArrayList(
                iModelOfClient.getLocalClientState().getGameTypeSet()
                        .stream()
                        .map(ModelOfServerDescriptor::getGameName)
                        .toList()
        ));
*/

        doOnThisState();
    }

    // 4
    @Override
    public void updateOnForgetGameType() {
        doOnThisState();
    }

    @Override
    public void updateOnSelectGameType() {
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
        textFieldSelectedGameType.setText(transportOfClient.getLocalClientState().getGameType().getGameName());
        super.doOnNextState();
    }
}
