package timmax.tilegame.client.statuscontrol;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.java_websocket.handshake.ServerHandshake;

import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.websocket.client.*;

public class Pane02UserLogin extends HBox implements
        Observer010OnClose,
        Observer011OnOpen,
        Observer020OnLogout,
        Observer021OnLogin {

    MultiGameWebSocketClient netModel;

    Label labelUser;
    TextField textFieldUser;
    Label labelPassword;
    PasswordField passwordField;
    Button buttonLogin;
    Button buttonLogout;


    public Pane02UserLogin() {
        labelUser = new Label("User");
        textFieldUser = new TextField();
        labelPassword = new Label("Password");
        passwordField = new PasswordField();
        buttonLogin = new Button("Login");
        buttonLogout = new Button("Logout");

        {   // Инициализация для отладки. Потом убрать совсем, либо через конфигурационный файл, но только имя (не пароль)!
            textFieldUser.setText("u1");
            passwordField.setText("1");
        }

        getChildren().addAll(labelUser, textFieldUser, labelPassword, passwordField, buttonLogin, buttonLogout);

        buttonLogin.setOnAction(event -> {
            System.out.println("buttonLogin.action. event = " + event);

            textFieldUser.setDisable(true);
            passwordField.setDisable(true);
            buttonLogin.setDisable(true);
            buttonLogout.setDisable(true);

            // netModel.login("u1", "1");
            netModel.login( textFieldUser.getText( ), passwordField.getText( ));
        });
        updateOnClose();
    }

    @Override
    public void updateOnClose() {
        System.out.println("Pane02ConnectNonIdent::updateOnClose");

        textFieldUser.setDisable(true);
        passwordField.setDisable(true);
        buttonLogin.setDisable(true);
        buttonLogout.setDisable(true);

        this.netModel = null;
    }

    // Для этой группы контролов функционал установки доступности этих контролов при updateOnOpen и при updateOnLogout одинаков
    @Override
    public void updateOnOpen(ServerHandshake handshakedata, MultiGameWebSocketClient multiGameWebSocketClient) {
        System.out.println("Pane02ConnectNonIdent::updateOnOpen");

        textFieldUser.setDisable(false);
        passwordField.setDisable(false);
        buttonLogin.setDisable(false);
        buttonLogout.setDisable(true);

        this.netModel = multiGameWebSocketClient;
        multiGameWebSocketClient.addViewOnClose(this);
        // multiGameWebSocketClient.addViewOnOpen(this); // Не обязательно

        // multiGameWebSocketClient.addViewOnLogout(this);
        multiGameWebSocketClient.addViewOnLogin(this);
    }

    @Override
    public void updateOnLogout() {
        System.out.println("Pane02ConnectNonIdent::updateOnLogout");

        textFieldUser.setDisable(true);
        passwordField.setDisable(true);
        buttonLogin.setDisable(true);
        buttonLogout.setDisable(false);
    }

    @Override
    public void updateOnLogin(ResultOfCredential resultOfCredential) {
        System.out.println("Pane02ConnectNonIdent::updateOnLogin");

        boolean loginDisabled = resultOfCredential == ResultOfCredential.AUTHORISED;
        textFieldUser.setDisable(loginDisabled);
        passwordField.setDisable(loginDisabled);
        buttonLogin.setDisable(loginDisabled);
        buttonLogout.setDisable(!loginDisabled);
    }
}