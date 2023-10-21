module WebSocketClient {
    requires org.java_websocket;
    requires com.fasterxml.jackson.databind;
    requires timmax.tilegame.basetilemodel;
    requires timmax.tilegame.game.minesweeper.model;
    requires timmax.tilegame.game.sokoban.model;

    exports timmax.tilegame.websocket.client;
}