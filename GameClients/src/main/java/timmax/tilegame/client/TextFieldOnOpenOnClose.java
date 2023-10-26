package timmax.tilegame.client;

import javafx.scene.control.TextField;
import org.java_websocket.handshake.ServerHandshake;
import timmax.tilegame.websocket.client.MultiGameWebSocketClientObserverOnClose;
import timmax.tilegame.websocket.client.MultiGameWebSocketClientObserverOnOpen;

public class TextFieldOnOpenOnClose extends TextField implements MultiGameWebSocketClientObserverOnOpen, MultiGameWebSocketClientObserverOnClose {
    public TextFieldOnOpenOnClose( String text) {
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