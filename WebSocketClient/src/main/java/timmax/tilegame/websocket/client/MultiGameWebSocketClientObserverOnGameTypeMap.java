package timmax.tilegame.websocket.client;

import java.util.Map;

public interface MultiGameWebSocketClientObserverOnGameTypeMap {
    void updateOnGameTypeMap( Map< Class, String> map);
}