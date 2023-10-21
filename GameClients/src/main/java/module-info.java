module timmax.tilegame.client {
    requires javafx.graphics;
    requires org.slf4j;
    requires timmax.tilegame.basetilemodel;
    requires timmax.tilegame.guiengine.jfx;
    requires timmax.tilegame.game.minesweeper.model;
    requires timmax.tilegame.game.minesweeper.jfx;
    requires timmax.tilegame.game.sokoban.jfx;

    exports timmax.tilegame.client;
}