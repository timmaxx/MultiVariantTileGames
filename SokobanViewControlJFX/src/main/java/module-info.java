module timmax.sokoban.jfx {
    requires timmax.basetilemodel;
    requires timmax.sokoban.model;
    requires timmax.tilegameenginejfx;
    requires javafx.graphics;
    requires javafx.controls;

    exports timmax.sokoban;
    opens timmax.sokoban;
}