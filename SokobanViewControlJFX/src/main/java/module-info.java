module timmax.tilegame.game.sokoban.jfx {
    requires org.slf4j;
    requires javafx.graphics;

    requires timmax.tilegame.basetilemodel;
    requires timmax.tilegame.guiengine.jfx;
    requires timmax.tilegame.game.sokoban.model;

    exports timmax.tilegame.game.sokoban.jfx.view;
}
