package timmax.tilegame.client.statuscontrol;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.java_websocket.handshake.ServerHandshake;

import timmax.tilegame.websocket.client.*;

public class Pane01ServerConnect extends HBox implements
        Observer011OnOpen,
        Observer010OnClose {
    private MultiGameWebSocketClient netModel;

    private final Label labelProtocol;
    private final TextField textFieldServerAddress;
    private final TextField textFieldServerPort;
    private final Button buttonConnect;
    private final Label labelConnectString;
    private final Button buttonDisconnect;

    // private Map<Observer020OnLogout, String> mapOfObserver020OnLogout__String;
    private Map<Observer011OnOpen, String> mapOfObserver011OnOpen__String;


    public Pane01ServerConnect() {
        super();

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

        getChildren().addAll(labelProtocol, textFieldServerAddress, textFieldServerPort, buttonConnect, labelConnectString, buttonDisconnect);

        buttonConnect.setOnAction(event -> {
            netModel = new MultiGameWebSocketClient(getURIFromControls());

            netModel.addViewOnClose(this);
            netModel.addViewOnOpen(this);

            for (Observer011OnOpen observer011OnOpen : mapOfObserver011OnOpen__String.keySet()) {
                netModel.addViewOnOpen(observer011OnOpen);
            }

            textFieldServerAddress.setDisable(true);
            textFieldServerPort.setDisable(true);
            buttonConnect.setDisable(true);
            buttonDisconnect.setDisable(true);

            netModel.connect();
        });

        buttonDisconnect.setOnAction(event -> netModel.close());

        updateOnClose();
    }

    public void setMapOfObserver011OnOpen__String(Map<Observer011OnOpen, String> mapOfObserver011OnOpen__String) {
        this.mapOfObserver011OnOpen__String = mapOfObserver011OnOpen__String;
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
        textFieldServerAddress.setDisable(false);
        textFieldServerPort.setDisable(false);
        buttonConnect.setDisable(false);
        buttonDisconnect.setDisable(true);

        netModel = null;
    }

    @Override
    public void updateOnOpen(ServerHandshake handshakedata, MultiGameWebSocketClient multiGameWebSocketClient) {
        textFieldServerAddress.setDisable(true);
        textFieldServerPort.setDisable(true);
        buttonConnect.setDisable(true);
        buttonDisconnect.setDisable(false);
    }
}