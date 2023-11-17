package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.*;

import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.websocket.client.*;

public class Pane02UserLogin extends AbstractConnectStatePane implements
        Observer010OnClose,
        Observer011OnOpen,
        Observer020OnLogout,
        Observer021OnLogin {

    private final PasswordField passwordField;


    public Pane02UserLogin(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        Label labelUser = new Label("User");
        TextField textFieldUser = new TextField();
        // private final TextField textFieldUser;
        Label labelPassword = new Label("Password");
        passwordField = new PasswordField();
        Button buttonLogin = new Button("Login");
        Button buttonLogout = new Button("Logout");

        {   // Инициализация для отладки. Потом убрать совсем, либо через конфигурационный файл, но только имя (не пароль)!
            textFieldUser.setText("u1");
            passwordField.setText("1");
        }

        multiGameWebSocketClientManyTimesUse.addViewOnClose(this);
        multiGameWebSocketClientManyTimesUse.addViewOnOpen(this);
        multiGameWebSocketClientManyTimesUse.addViewOnLogout(this);
        multiGameWebSocketClientManyTimesUse.addViewOnLogin(this);

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
                List.of(buttonLogout));
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
    public void updateOnLogin(ResultOfCredential resultOfCredential) {
        boolean loginDisabled = resultOfCredential == ResultOfCredential.AUTHORISED;
        setDisableControlsNextState(loginDisabled);
    }
}