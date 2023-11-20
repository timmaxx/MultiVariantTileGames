module timmax.tilegame.client {
    requires org.slf4j;
    requires javafx.graphics;
    requires javafx.controls;

    requires timmax.tilegame.websocket.client;
    requires timmax.tilegame.basetilemodel;

    //  It needs this dependency at real-time for creating list of game classes
    requires timmax.tilegame.game.minesweeper.model;
    //  It needs this dependency at real-time for creating list of game classes
    requires timmax.tilegame.game.sokoban.model;

    exports timmax.tilegame.client;
}