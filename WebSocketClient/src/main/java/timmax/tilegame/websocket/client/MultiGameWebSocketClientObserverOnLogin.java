package timmax.tilegame.websocket.client;

import timmax.tilegame.basemodel.credential.ResultOfCredential;

public interface MultiGameWebSocketClientObserverOnLogin {
    void updateOnLogin( ResultOfCredential resultOfCredential);
}