package timmax.tilegame.websocket.client;

import org.java_websocket.handshake.ServerHandshake;

public interface MultiGameWebSocketClientObserverOnOpen {
    void updateOnOpen( ServerHandshake handshakedata);
}