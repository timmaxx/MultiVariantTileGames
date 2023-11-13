package timmax.tilegame.client.statuscontrol;

import java.net.URI;
import java.net.URISyntaxException;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.java_websocket.handshake.ServerHandshake;

import timmax.tilegame.websocket.client.*;

public class Pane01ServerConnect extends HBox implements
        Observer010OnClose,
        Observer011OnOpen {

    private final MultiGameWebSocketClientManyTimesUse netModel;

    private final Label labelProtocol;
    private final TextField textFieldServerAddress;
    private final TextField textFieldServerPort;
    private final Button buttonConnect;
    private final Label labelConnectString;
    private final Button buttonDisconnect;


    public Pane01ServerConnect(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super();
        this.netModel = multiGameWebSocketClientManyTimesUse;

        labelProtocol = new Label("ws");
        textFieldServerAddress = new TextField();
        textFieldServerPort = new TextField();
        buttonConnect = new Button("Connect");
        labelConnectString = new Label();
        buttonDisconnect = new Button("Disconnect");

        {   // Инициализация для отладки. Потом либо убрать, либо через конфигурационный файл!
            textFieldServerAddress.setText("localhost");
            textFieldServerPort.setText("8887");
        }

        updateOnClose();
        getChildren().addAll(labelProtocol, textFieldServerAddress, textFieldServerPort, buttonConnect, labelConnectString, buttonDisconnect);

        netModel.addViewOnClose(this);
        netModel.addViewOnOpen(this);

        buttonConnect.setOnAction(event -> {
            netModel.setURI(getURIFromControls());
            disableAllControls();
            netModel.connect();
        });

        buttonDisconnect.setOnAction(event -> {
            disableAllControls();
            netModel.close();
        });
    }

    public URI getURIFromControls() {
        labelConnectString.setText(labelProtocol.getText() + "://" + textFieldServerAddress.getText() + ":" + textFieldServerPort.getText());
        try {
            return new URI(labelConnectString.getText());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void disableAllControls() {
        textFieldServerAddress.setDisable(true);
        textFieldServerPort.setDisable(true);
        buttonConnect.setDisable(true);
        buttonDisconnect.setDisable(true);
    }

    @Override
    public void updateOnClose() {
        textFieldServerAddress.setDisable(false);
        textFieldServerPort.setDisable(false);
        buttonConnect.setDisable(false);
        buttonDisconnect.setDisable(true);
    }

    @Override
    public void updateOnOpen(ServerHandshake handshakedata) {
        textFieldServerAddress.setDisable(true);
        textFieldServerPort.setDisable(true);
        buttonConnect.setDisable(true);
        buttonDisconnect.setDisable(false);
    }
}