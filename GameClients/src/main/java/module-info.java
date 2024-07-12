module timmax.tilegame.client {
    requires org.slf4j;
    requires javafx.graphics;
    requires javafx.controls;

    requires timmax.tilegame.websocket.client;
    requires timmax.tilegame.basetilemodel;
    requires timmax.tilegame.guiengine.jfx;

    exports timmax.tilegame.client;
}
