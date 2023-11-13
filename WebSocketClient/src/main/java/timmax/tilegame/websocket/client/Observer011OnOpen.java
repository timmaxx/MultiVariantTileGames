package timmax.tilegame.websocket.client;

import org.java_websocket.handshake.ServerHandshake;

public interface Observer011OnOpen {
    void updateOnOpen(ServerHandshake handshakedata);
}