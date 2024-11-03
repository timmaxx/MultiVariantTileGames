module timmax.tilegame.sokoban.jfx {
    requires org.slf4j;
    requires javafx.graphics;

    requires timmax.tilegame.basetilemodel;
    requires timmax.tilegame.guiengine.jfx;
    requires timmax.tilegame.sokoban.model;

    exports timmax.tilegame.sokoban.jfx.view;
}
