module timmax.tilegame.basetilemodel {
    requires org.slf4j;
    requires javafx.graphics;
    requires timmax.common;

    exports timmax.tilegame.basemodel;
    exports timmax.tilegame.baseview;
    exports timmax.tilegame.basecontroller;
    exports timmax.tilegame.basemodel.tile;
    exports timmax.tilegame.basemodel.gameevent;
    exports timmax.tilegame.basemodel.gamecommand;
    exports timmax.tilegame.basemodel.protocol;
    exports timmax.tilegame.basemodel.credential;
    exports timmax.tilegame.basemodel.protocol.exception;
    exports timmax.tilegame.basemodel.protocol.server;
    exports timmax.tilegame.basemodel.protocol.client;
    exports timmax.tilegame.basemodel.protocol.server_client;
    exports timmax.tilegame.transport;

    opens timmax.tilegame.basemodel.protocol.server;
}
