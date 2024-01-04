module timmax.tilegame.game.minesweeper.jfx {
    requires javafx.graphics;
    requires org.slf4j;
    requires timmax.tilegame.basetilemodel;
    requires timmax.tilegame.guiengine.jfx;
    requires timmax.tilegame.game.minesweeper.model;

    exports timmax.tilegame.game.minesweeper.jfx.view;
    exports timmax.tilegame.game.minesweeper.jfx;
}
