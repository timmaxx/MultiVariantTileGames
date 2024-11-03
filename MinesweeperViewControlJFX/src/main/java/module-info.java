module timmax.tilegame.game.minesweeper.jfx {
    requires org.slf4j;
    requires javafx.graphics;

    requires timmax.tilegame.basetilemodel;
    requires timmax.tilegame.guiengine.jfx;
    requires timmax.tilegame.minesweeper.model;

    exports timmax.tilegame.minesweeper.jfx.view;
}
