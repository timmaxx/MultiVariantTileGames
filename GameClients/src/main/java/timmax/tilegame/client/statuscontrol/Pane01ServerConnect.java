package timmax.tilegame.client.statuscontrol;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.transport.TransportOfClient;

public class Pane01ServerConnect extends AbstractConnectStatePane {
    private final TextField textFieldServerAddress;
    private final TextField textFieldServerPort;
    private final TextField textFieldConnectString;

    public Pane01ServerConnect(IModelOfClient iModelOfClient, TransportOfClient transportOfClient) {
        super(iModelOfClient, transportOfClient);

        // Контролы для продвижения состояния "вперёд":
        Label labelServerAddress = new Label("Address");
        textFieldServerAddress = new TextField();
        Label labelServerPort = new Label("Port");
        textFieldServerPort = new TextField();
        textFieldConnectString = new TextField();
        textFieldConnectString.setEditable(false);
        {   // Инициализация для отладки. Потом либо убрать, либо через конфигурационный файл!
            textFieldServerAddress.setText("localhost");
            textFieldServerPort.setText("8887");
        }

        buttonNextState.setText("Connect");
        buttonNextState.setOnAction(event -> {
            disableAllControls();
            // ToDo: С методом setURI() нужно разобраться - включать или не включать его в интерфейс?
            transportOfClient.setURI(getURIFromControls());
            transportOfClient.connect();
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Disconnect");
        buttonPrevState.setFocusTraversable(false);
        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            transportOfClient.close();
        });

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(labelServerAddress, textFieldServerAddress, labelServerPort, textFieldServerPort, textFieldConnectString),
                List.of()
        );
        setDisableControlsNextState(false);
    }

    public URI getURIFromControls() {
        textFieldConnectString.setText("ws://" + textFieldServerAddress.getText() + ":" + textFieldServerPort.getText());
        try {
            return new URI(textFieldConnectString.getText());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    // Implemented methods of interface ObserverOnAbstractEvent
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
