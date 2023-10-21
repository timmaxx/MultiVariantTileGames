module timmax.tilegame.guiengine.jfx {
    requires javafx.graphics;
    requires timmax.tilegame.basetilemodel;
    requires WebSocketClient;

    exports timmax.tilegame.guiengine.jfx;
    exports timmax.tilegame.guiengine.jfx.view;
    exports timmax.tilegame.guiengine.jfx.controller;
}