package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.*;

public class Pane02ConnectNonIdent extends AbstractConnectStatePane {
    public Pane02ConnectNonIdent(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        Label userLabel = new Label("User");
        TextField userNameTextField = new TextField();
        Label passwordLabel = new Label("Password");
        PasswordField userPasswordField = new PasswordField();
        {   // Инициализация для отладки. Потом убрать совсем, либо через конфигурационный файл, но только имя (не пароль)!
            userNameTextField.setText("u1");
            userPasswordField.setText("1");
        }

        nextStateButton.setText("Login");
        nextStateButton.setOnAction(event -> {
            disableAllControls();
            transportOfClient.setUser(userNameTextField.getText(), userPasswordField.getText());
        });

        // Контролы для продвижения состояния "назад":
        prevStateButton.setText("Logout");
        prevStateButton.setFocusTraversable(false);
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            transportOfClient.forgetUser();
        });

        // 1
        userLabel.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        userLabel.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);
        userNameTextField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        userNameTextField.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);

        // 2
        passwordLabel.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        passwordLabel.setLayoutY(DIFFERENCE_OF_LAYOUT_Y);
        userPasswordField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        userPasswordField.setLayoutY(DIFFERENCE_OF_LAYOUT_Y);

        // Получилось 2 строки контролов:
        nextStatePane.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * 2);
        nextStatePane.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * 2);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(userLabel, userNameTextField, passwordLabel, userPasswordField),
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
        doOnThisState();
    }

    // 2
    @Override
    public void updateOnForgetGameTypeSet() {
        doOnThisState();
    }

    @Override
    public void updateOnSetUser() {
        doOnNextState();
    }
}
