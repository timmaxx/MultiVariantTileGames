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

    private final MultiGameWebSocketClientManyTimesUse netModel;

    private final Label labelUser;
    private final TextField textFieldUser;
    private final Label labelPassword;
    private final PasswordField passwordField;
    private final Button buttonLogin;
    private final Button buttonLogout;


    public Pane02UserLogin(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super();
        this.netModel = multiGameWebSocketClientManyTimesUse;

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

        updateOnClose();
        getChildren().addAll(labelUser, textFieldUser, labelPassword, passwordField, buttonLogin, buttonLogout);

        netModel.addViewOnClose(this);
        netModel.addViewOnOpen(this);
        netModel.addViewOnLogout(this);
        netModel.addViewOnLogin(this);

        buttonLogin.setOnAction(event -> {
            disableAllControls();
            netModel.login(textFieldUser.getText(), passwordField.getText());
        });

        buttonLogout.setOnAction(event -> {
            disableAllControls();
            netModel.logout();
        });
    }

    private void disableAllControls() {
        textFieldUser.setDisable(true);
        passwordField.setDisable(true);
        buttonLogin.setDisable(true);
        buttonLogout.setDisable(true);
    }

    @Override
    public void updateOnClose() {
        disableAllControls();
    }

    // Для этой группы контролов функционал установки доступности этих контролов при updateOnOpen и при updateOnLogout одинаков
    @Override
    public void updateOnOpen(ServerHandshake handshakedata) {
        textFieldUser.setDisable(false);
        passwordField.setDisable(false);
        buttonLogin.setDisable(false);
        buttonLogout.setDisable(true);
    }

    @Override
    public void updateOnLogout() {
        textFieldUser.setDisable(false);
        passwordField.setDisable(false);
        buttonLogin.setDisable(false);
        buttonLogout.setDisable(true);
    }

    @Override
    public void updateOnLogin(ResultOfCredential resultOfCredential) {
        boolean loginDisabled = resultOfCredential == ResultOfCredential.AUTHORISED;
        textFieldUser.setDisable(loginDisabled);
        passwordField.setDisable(loginDisabled);
        buttonLogin.setDisable(loginDisabled);
        buttonLogout.setDisable(!loginDisabled);
    }
}