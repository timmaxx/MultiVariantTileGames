module timmax.tilegame.client {
    requires org.slf4j;
    requires javafx.graphics;
    requires javafx.controls;

    requires timmax.tilegame.websocket.client;
    requires timmax.tilegame.basetilemodel;
    requires timmax.tilegame.guiengine.jfx;

    requires timmax.tilegame.game.minesweeper.model;
    requires timmax.tilegame.game.minesweeper.jfx;
    requires timmax.tilegame.game.sokoban.jfx;

    exports timmax.tilegame.client;
}