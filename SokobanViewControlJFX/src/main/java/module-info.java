module timmax.tilegame.game.sokoban.jfx {
    requires javafx.graphics;
    requires javafx.controls;
    requires org.slf4j;
    requires timmax.tilegame.basetilemodel;
    requires timmax.tilegame.guiengine.jfx;
    requires timmax.tilegame.game.sokoban.model;

    exports timmax.tilegame.game.sokoban.jfx.view;
    exports timmax.tilegame.game.sokoban.jfx.controller;
}