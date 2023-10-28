package timmax.tilegame.client;

import javafx.scene.control.TextField;

import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.websocket.client.MultiGameWebSocketClientObserverOnLogin;
import timmax.tilegame.websocket.client.MultiGameWebSocketClientObserverOnLogout;

public class TextFieldOnLoginOnLogout extends TextField implements MultiGameWebSocketClientObserverOnLogin, MultiGameWebSocketClientObserverOnLogout {
    public TextFieldOnLoginOnLogout( String text) {
        super( text);
    }

    @Override
    public void updateOnLogin( ResultOfCredential resultOfCredential) {
        // setDisable( true);
        setDisable( resultOfCredential != ResultOfCredential.AUTHORISED);
    }

    @Override
    public void updateOnLogout( ) {
        setDisable( false);
    }
}