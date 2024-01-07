package timmax.tilegame.client.statuscontrol;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane01ServerConnect extends AbstractConnectStatePane {
    private final TextField textFieldServerAddress;
    private final TextField textFieldServerPort;
    private final Label labelConnectString;


    public Pane01ServerConnect(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        Label labelServerAddress = new Label("Address");
        textFieldServerAddress = new TextField();
        Label labelServerPort = new Label("Port");
        textFieldServerPort = new TextField();
        Button buttonConnect = new Button("Connect");
        labelConnectString = new Label();
        Button buttonDisconnect = new Button("Disconnect");

        buttonDisconnect.setFocusTraversable(false);

        {   // Инициализация для отладки. Потом либо убрать, либо через конфигурационный файл!
            textFieldServerAddress.setText("localhost");
            textFieldServerPort.setText("8887");
        }

        buttonConnect.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.setURI(getURIFromControls());
            multiGameWebSocketClientManyTimesUse.connect();
        });

        buttonDisconnect.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.close();
        });

        setListsOfControlsAndAllDisable(
                List.of(labelServerAddress, textFieldServerAddress, labelServerPort, textFieldServerPort, buttonConnect, labelConnectString),
                List.of(buttonDisconnect)
        );
        setDisableControlsNextState(false);
    }

    public URI getURIFromControls() {
        labelConnectString.setText("ws://" + textFieldServerAddress.getText() + ":" + textFieldServerPort.getText());
        try {
            return new URI(labelConnectString.getText());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateOnClose() {
        setDisableControlsNextState(false);
    }

    @Override
    protected void updateOnOpen() {
        setDisableControlsNextState(true);
    }
}
