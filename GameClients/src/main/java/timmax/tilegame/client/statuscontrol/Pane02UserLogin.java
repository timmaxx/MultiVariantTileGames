package timmax.tilegame.client.statuscontrol;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.transport.TransportOfClient;

public class Pane02UserLogin extends AbstractConnectStatePane {
    public Pane02UserLogin(IModelOfClient iModelOfClient, TransportOfClient transportOfClient) {
        super(iModelOfClient, transportOfClient);

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
            iModelOfClient.login(textFieldUser.getText(), passwordField.getText());
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Logout");
        buttonPrevState.setFocusTraversable(false);
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            iModelOfClient.logout();
        });

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
    public void updateOnLogout() {
        doOnThisState();
    }

    @Override
    public void updateOnLogin() {
        doOnNextState();
    }
}
