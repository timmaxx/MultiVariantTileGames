package timmax.tilegame.client.statuscontrol;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import timmax.tilegame.websocket.client.*;

public class Pane01ServerConnect extends AbstractConnectStatePane implements
        Observer010OnClose,
        Observer011OnOpen {

    private final Label labelProtocol;
    private final TextField textFieldServerAddress;
    private final TextField textFieldServerPort;
    private final Label labelConnectString;


    public Pane01ServerConnect(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        labelProtocol = new Label("ws");
        textFieldServerAddress = new TextField();
        textFieldServerPort = new TextField();
        Button buttonConnect = new Button("Connect");
        labelConnectString = new Label();
        Button buttonDisconnect = new Button("Disconnect");

        {   // Инициализация для отладки. Потом либо убрать, либо через конфигурационный файл!
            textFieldServerAddress.setText("localhost");
            textFieldServerPort.setText("8887");
        }

        multiGameWebSocketClientManyTimesUse.addViewOnClose(this);
        multiGameWebSocketClientManyTimesUse.addViewOnOpen(this);

        buttonConnect.setOnAction(event -> {
            multiGameWebSocketClientManyTimesUse.setURI(getURIFromControls());
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.connect();
        });

        buttonDisconnect.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.close();
        });

        setListsOfControlsAndAllDisable(
                List.of(labelProtocol, textFieldServerAddress, textFieldServerPort, buttonConnect, labelConnectString),
                List.of(buttonDisconnect)
        );
        setDisableControlsNextState(false);
    }

    public URI getURIFromControls() {
        labelConnectString.setText(labelProtocol.getText() + "://" + textFieldServerAddress.getText() + ":" + textFieldServerPort.getText());
        try {
            return new URI(labelConnectString.getText());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOnClose() {
        setDisableControlsNextState(false);
    }

    @Override
    public void updateOnOpen() {
        setDisableControlsNextState(true);
    }
}