module WebSocketClient {
    requires org.java_websocket;
    requires com.fasterxml.jackson.databind;
    requires timmax.tilegame.basetilemodel;

    exports timmax.tilegame.websocket.client;
}