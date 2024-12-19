package timmax.tilegame.client.statuscontrol;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import timmax.tilegame.transport.ISenderOfEventOfClient;

public class Pane01NoConnect extends AbstractConnectStatePane {
    private final TextField serverAddressTextField;
    private final TextField serverPortTextField;
    private final TextField connectStringTextField;

    public Pane01NoConnect(ISenderOfEventOfClient senderOfEventOfClient) {
        super(senderOfEventOfClient);

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
            senderOfEventOfClient.setURI(getURIFromControls());
            senderOfEventOfClient.connect();
        });

        // Контролы для продвижения состояния "назад":
        prevStateButton.setText("Disconnect");
        prevStateButton.setFocusTraversable(false);
        prevStateButton.setOnAction(event -> {
            disableAllControls();
            senderOfEventOfClient.close();
        });

        // 1
        int y = LAYOUT_Y_OF_FIRST_ROW;
        serverAddressLabel.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        serverAddressLabel.setLayoutY(y);
        serverAddressTextField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        serverAddressTextField.setLayoutY(y);

        // 2
        y += DIFFERENCE_OF_LAYOUT_Y;
        serverPortLabel.setLayoutX(LAYOUT_X_OF_FIRST_COLUMN);
        serverPortLabel.setLayoutY(y);
        serverPortTextField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        serverPortTextField.setLayoutY(y);

        // 3
        y += DIFFERENCE_OF_LAYOUT_Y;
        connectStringTextField.setLayoutX(LAYOUT_X_OF_SECOND_COLUMN);
        connectStringTextField.setLayoutY(y);

        // Получилось 3 строки контролов:
        y += DIFFERENCE_OF_LAYOUT_Y;
        nextStatePane.setMinHeight(y);
        nextStatePane.setMaxHeight(y);

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контролов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(serverAddressLabel, serverAddressTextField, serverPortLabel, serverPortTextField, connectStringTextField),
                List.of()
        );

        doOnThisState();
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
