package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.*;

import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane02UserLogin extends AbstractConnectStatePane {
    private final PasswordField passwordField;

    public Pane02UserLogin(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        Label labelUser = new Label("User");
        TextField textFieldUser = new TextField();
        Label labelPassword = new Label("Password");
        passwordField = new PasswordField();
        Button buttonLogin = new Button("Login");
        Button buttonLogout = new Button("Logout");

        buttonLogout.setFocusTraversable(false);

        {   // Инициализация для отладки. Потом убрать совсем, либо через конфигурационный файл, но только имя (не пароль)!
            textFieldUser.setText("u1");
            passwordField.setText("1");
        }

        buttonLogin.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.login(textFieldUser.getText(), passwordField.getText());
        });

        buttonLogout.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.logout();
        });

        setListsOfControlsAndAllDisable(
                List.of(labelUser, textFieldUser, labelPassword, passwordField, buttonLogin),
                List.of(buttonLogout)
        );
    }

    @Override
    public void updateOnClose() {
        disableAllControls();
    }

    @Override
    public void updateOnOpen() {
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnLogout() {
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnLogin() {
        setDisableControlsNextState(true);
    }
}
