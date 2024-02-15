package timmax.tilegame.client.statuscontrol;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import timmax.tilegame.client.websocket.MultiGameWebSocketClientManyTimesUse;

public class Pane01ServerConnect extends AbstractConnectStatePane {
    private final TextField textFieldServerAddress;
    private final TextField textFieldServerPort;
    private final Label labelConnectString;

    public Pane01ServerConnect(MultiGameWebSocketClientManyTimesUse multiGameWebSocketClientManyTimesUse) {
        super(multiGameWebSocketClientManyTimesUse);

        // Контролы для продвижения состояния "вперёд":
        Label labelServerAddress = new Label("Address");
        textFieldServerAddress = new TextField();
        Label labelServerPort = new Label("Port");
        textFieldServerPort = new TextField();
        buttonNextState.setText("Connect");
        labelConnectString = new Label();

        {   // Инициализация для отладки. Потом либо убрать, либо через конфигурационный файл!
            textFieldServerAddress.setText("localhost");
            textFieldServerPort.setText("8887");
        }

        buttonNextState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.setURI(getURIFromControls());
            multiGameWebSocketClientManyTimesUse.connect();
        });

        // Контролы для продвижения состояния "назад":
        buttonPrevState.setText("Disconnect");
        buttonPrevState.setFocusTraversable(false);

        buttonPrevState.setOnAction(event -> {
            disableAllControls();
            multiGameWebSocketClientManyTimesUse.close();
        });

        // Вызов setListsOfControlsAndAllDisable() нужен для разделения контроллов на два перечня: "вперёд" и "назад".
        setListsOfControlsAndAllDisable(
                List.of(labelServerAddress, textFieldServerAddress, labelServerPort, textFieldServerPort, buttonNextState, labelConnectString),
                List.of(buttonPrevState)
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
    public void updateOnClose() {
        doOnPrevState();
    }

    @Override
    public void updateOnOpen() {
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
