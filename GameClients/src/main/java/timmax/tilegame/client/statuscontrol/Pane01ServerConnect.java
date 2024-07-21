package timmax.tilegame.client.statuscontrol;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import timmax.tilegame.transport.TransportOfClient;

import static timmax.tilegame.guiengine.jfx.view.ViewMainFieldJfx.*;

// ToDo: Переименовать класс.
// Панель соответствует состоянию 01NoConnect
public class Pane01ServerConnect extends AbstractConnectStatePane {
    private final TextField serverAddressTextField;
    private final TextField serverPortTextField;
    private final TextField connectStringTextField;

    public Pane01ServerConnect(TransportOfClient transportOfClient) {
        super(transportOfClient);

        // 1 (обязательные)
        // Контролы для продвижения состояния "вперёд":
        Label serverAddressLabel = new Label("Address");
        serverAddressTextField = new TextField();
        Label serverPortLabel = new Label("Port");
        serverPortTextField = new TextField();
        connectStringTextField = new TextField();
        connectStringTextField.setEditable(false);
        {   // Инициализация для отладки. Потом либо убрать, либо через конфигурационный файл!
            serverAddressTextField.setText("localhost");
            serverPortTextField.setText("8887");
        }

        nextStateButton.setText("Connect");
        nextStateButton.setOnAction(event -> {
            disableAllControls();
            // ToDo: С методом setURI() нужно разобраться - включать или не включать его в интерфейс?
            transportOfClient.setURI(getURIFromControls());
            transportOfClient.connect();
        });

        // Контролы для продвижения состояния "назад":
        prevStateButton.setText("Disconnect");
        prevStateButton.setFocusTraversable(false);
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            transportOfClient.close();
        });

        // 1
        serverAddressLabel.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        serverAddressLabel.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);
        serverAddressTextField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        serverAddressTextField.setLayoutY(LAYOUT_Y_OF_FIRST_ROW);

        // 2
        serverPortLabel.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        serverPortLabel.setLayoutY(DIFFERENCE_OF_LAYOUT_Y);
        serverPortTextField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        serverPortTextField.setLayoutY(DIFFERENCE_OF_LAYOUT_Y);

        // 3
        connectStringTextField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        connectStringTextField.setLayoutY(2 * DIFFERENCE_OF_LAYOUT_Y);

        // Получилось 3 строки контролов:
        nextStatePane.setPrefHeight(DIFFERENCE_OF_LAYOUT_Y * 3);
        nextStatePane.setMinHeight(DIFFERENCE_OF_LAYOUT_Y * 3);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(serverAddressLabel, serverAddressTextField, serverPortLabel, serverPortTextField, connectStringTextField),
                List.of()
        );
        // ToDo: метод с параметром 'false' нужно вызывать, когда панель соответствует состоянию.
        setDisableNextStateControls(false);
    }

    public URI getURIFromControls() {
        connectStringTextField.setText("ws://" + serverAddressTextField.getText() + ":" + serverPortTextField.getText());
        try {
            return new URI(connectStringTextField.getText());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // interface ObserverOnAbstractEvent
    // 1
    @Override
    public void updateOnClose() {
        doOnThisState();
    }

    @Override
    public void updateOnOpen() {
        doOnNextState();
    }
}
