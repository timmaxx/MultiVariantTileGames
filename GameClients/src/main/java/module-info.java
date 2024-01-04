module timmax.tilegame.client {
    requires org.slf4j;
    requires javafx.graphics;
    requires javafx.controls;

    requires timmax.tilegame.websocket.client;
    requires timmax.tilegame.basetilemodel;
    requires timmax.tilegame.guiengine.jfx;

    //  It needs this dependency at real-time for creating list of game classes
    requires timmax.tilegame.game.minesweeper.model;
    requires timmax.tilegame.game.sokoban.model;

    requires timmax.tilegame.game.sokoban.jfx;
    requires timmax.tilegame.game.minesweeper.jfx;

    exports timmax.tilegame.client;
}
