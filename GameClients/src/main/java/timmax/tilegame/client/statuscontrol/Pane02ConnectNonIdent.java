package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import timmax.tilegame.transport.ISenderOfEventOfClient;

public class Pane02ConnectNonIdent extends AbstractConnectStatePane {
    public Pane02ConnectNonIdent(ISenderOfEventOfClient senderOfEventOfClient) {
        super(senderOfEventOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        Label userIdLabel = new Label("User name");
        TextField userIdTextField = new TextField();
        Label userPasswordLabel = new Label("Password");
        PasswordField userPasswordField = new PasswordField();
        {   // Инициализация для отладки. Потом убрать совсем, либо через конфигурационный файл, но только имя (не пароль)!
            userIdTextField.setText("u1");
            userPasswordField.setText("1");
        }

        nextStateButton.setText("Login");
        nextStateButton.setOnAction(event -> {
            disableAllControls();
            senderOfEventOfClient.identifyAuthenticateAuthorizeUser(userIdTextField.getText(), userPasswordField.getText());
        });

        // Контролы для продвижения состояния "назад":
        prevStateButton.setText("Logout");
        prevStateButton.setFocusTraversable(false);
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            senderOfEventOfClient.connectWithoutUserIdentify();
        });

        // 1
        int y = LAYOUT_Y_OF_FIRST_ROW;
        userIdLabel.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        userIdLabel.setLayoutY(y);
        userIdTextField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        userIdTextField.setLayoutY(y);

        // 2
        y += DIFFERENCE_OF_LAYOUT_Y;
        userPasswordLabel.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        userPasswordLabel.setLayoutY(y);
        userPasswordField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        userPasswordField.setLayoutY(y);

        // Получилось 2 строки контролов:
        y += DIFFERENCE_OF_LAYOUT_Y;
        nextStatePane.setMinHeight(y);
        nextStatePane.setMaxHeight(y);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(userIdLabel, userIdTextField, userPasswordLabel, userPasswordField),
                List.of()
        );
    }

    // interface ObserverOnAbstractEvent
    // 1
    @Override
    public void updateOnOpen() {
        doOnThisState();
    }

    // 2
    @Override
    public void updateOnAuthorizeUser() {
        doOnNextState();
    }
}
