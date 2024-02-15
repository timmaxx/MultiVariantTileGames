package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane02UserLogin extends AbstractConnectStatePane {
    private final PasswordField passwordField;

    public Pane02UserLogin(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        // Контролы для продвижения состояния "вперёд":
        Label labelUser = new Label("User");
        TextField textFieldUser = new TextField();
        Label labelPassword = new Label("Password");
        passwordField = new PasswordField();
        buttonNextState.setText("Login");

        {   // Инициализация для отладки. Потом убрать совсем, либо через конфигурационный файл, но только имя (не пароль)!
            textFieldUser.setText("u1");
            passwordField.setText("1");
        }

        buttonNextState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.login(textFieldUser.getText(), passwordField.getText());
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Logout");
        buttonPrevState.setFocusTraversable(false);

        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.logout();
        });

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(labelUser, textFieldUser, labelPassword, passwordField, buttonNextState),
                List.of(buttonPrevState)
        );
    }

    @Override
    public void updateOnClose() {
        doOnPrevPrevState();
    }

    @Override
    public void updateOnOpen() {
        doOnPrevState();
    }

    @Override
    public void updateOnLogout() {
        doOnPrevState();
    }

    @Override
    public void updateOnLogin() {
        doOnNextState();
    }

    //
    protected void doOnPrevPrevState() {
        disableAllControls();
    }

    protected void doOnPrevState() {
        setDisableControlsNextState(false);
    }

    protected void doOnNextState() {
        setDisableControlsNextState(true);
    }
}
