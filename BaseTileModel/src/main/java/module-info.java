module timmax.tilegame.basetilemodel {
    requires javafx.graphics;
    requires org.slf4j;

    exports timmax.tilegame.basemodel;
    exports timmax.tilegame.baseview;
    exports timmax.tilegame.basecontroller;
    exports timmax.tilegame.basemodel.tile;
    exports timmax.tilegame.basemodel.gameevent;
    exports timmax.tilegame.basemodel.gamecommand;
    exports timmax.tilegame.basemodel.protocol;
    exports timmax.tilegame.basemodel.credential;
    exports timmax.tilegame.basemodel.protocol.exception;
    exports timmax.tilegame.transport;
    exports timmax.tilegame.basemodel.protocol.server;
    exports timmax.tilegame.basemodel.protocol.client;
    exports timmax.tilegame.basemodel.protocol.server_client;

    opens timmax.tilegame.basemodel.protocol.server;
}
