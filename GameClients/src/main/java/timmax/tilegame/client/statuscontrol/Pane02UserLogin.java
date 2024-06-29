package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import timmax.tilegame.transport.TransportOfClient;

public class Pane02UserLogin<ClientId> extends AbstractConnectStatePane<ClientId> {
    public Pane02UserLogin(TransportOfClient<ClientId> transportOfClient) {
        super(transportOfClient);

        // Контролы для продвижения состояния "вперёд":
        Label labelUser = new Label("User");
        TextField textFieldUser = new TextField();
        Label labelPassword = new Label("Password");
        PasswordField passwordField = new PasswordField();
        {   // Инициализация для отладки. Потом убрать совсем, либо через конфигурационный файл, но только имя (не пароль)!
            textFieldUser.setText("u1");
            passwordField.setText("1");
        }

        buttonNextState.setText("Login");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            transportOfClient.setUser(textFieldUser.getText(), passwordField.getText());
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Logout");
        buttonPrevState.setFocusTraversable(false);
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            transportOfClient.forgetUser();
        });

        labelUser.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        labelUser.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);
        textFieldUser.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        textFieldUser.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);

        labelPassword.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        labelPassword.setLayoutY(DIFFERENCE_OF_LAYOUT_Y);
        passwordField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        passwordField.setLayoutY(DIFFERENCE_OF_LAYOUT_Y);

        paneNextState.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * 2);
        paneNextState.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * 2);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(labelUser, textFieldUser, labelPassword, passwordField),
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
        doOnThisState();
    }

    // 2
    @Override
    public void updateOnForgetUser() {
        doOnThisState();
    }

    @Override
    public void updateOnSetUser() {
        doOnNextState();
    }
}
