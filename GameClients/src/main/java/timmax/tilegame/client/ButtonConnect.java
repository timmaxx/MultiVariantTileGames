package timmax.tilegame.client;

import javafx.scene.control.Button;
import org.java_websocket.handshake.ServerHandshake;
import timmax.tilegame.websocket.client.MultiGameWebSocketClientObserverOnClose;
import timmax.tilegame.websocket.client.MultiGameWebSocketClientObserverOnOpen;

public class ButtonConnect extends Button implements MultiGameWebSocketClientObserverOnOpen, MultiGameWebSocketClientObserverOnClose {
    public ButtonConnect( String text) {
        super( text);
    }

    @Override
    public void updateOnOpen( ServerHandshake handshakedata) {
        setDisable( true);
    }

    @Override
    public void updateOnClose() {
        setDisable( false);
    }
}