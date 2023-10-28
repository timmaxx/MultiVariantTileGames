package timmax.tilegame.client;

import javafx.scene.control.PasswordField;
import timmax.tilegame.basemodel.credential.ResultOfCredential;
import timmax.tilegame.websocket.client.MultiGameWebSocketClientObserverOnLogin;
import timmax.tilegame.websocket.client.MultiGameWebSocketClientObserverOnLogout;

public class PasswordFieldOnLoginOnLogout extends PasswordField implements MultiGameWebSocketClientObserverOnLogin, MultiGameWebSocketClientObserverOnLogout {
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