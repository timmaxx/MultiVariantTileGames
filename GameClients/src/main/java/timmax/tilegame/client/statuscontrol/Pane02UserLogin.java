package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.*;

import timmax.tilegame.basemodel.protocol.ObserverOnAbstractEvent;
import timmax.tilegame.websocket.client.*;

public class Pane02UserLogin extends AbstractConnectStatePane implements ObserverOnAbstractEvent {
    private final PasswordField passwordField;


    public Pane02UserLogin(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse.getClientState());

        Label labelUser = new Label("User");
        TextField textFieldUser = new TextField();
        Label labelPassword = new Label("Password");
        passwordField = new PasswordField();
        Button buttonLogin = new Button("Login");
        Button buttonLogout = new Button("Logout");

        {   // Инициализация для отладки. Потом убрать совсем, либо через конфигурационный файл, но только имя (не пароль)!
            textFieldUser.setText("u1");
            passwordField.setText("1");
        }

        multiGameWebSocketClientManyTimesUse.addViewOnAnyEvent(this);

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
    public void updateOnLogin() {
        setDisableControlsNextState(true);
    }
}