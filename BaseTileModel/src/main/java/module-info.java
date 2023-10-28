module timmax.tilegame.basetilemodel {
    requires com.fasterxml.jackson.annotation;

    exports timmax.tilegame.basemodel;
    exports timmax.tilegame.baseview;
    exports timmax.tilegame.basecontroller;
    exports timmax.tilegame.basemodel.tile;
    exports timmax.tilegame.basemodel.gameevent;
    exports timmax.tilegame.basemodel.gamecommand;
    exports timmax.tilegame.basemodel.protocol;
    exports timmax.tilegame.basemodel.clientappstatus;
    exports timmax.tilegame.basemodel.credential;
    exports timmax.tilegame.transport;

    opens timmax.tilegame.basemodel.gameevent to com.fasterxml.jackson.databind;
    opens timmax.tilegame.basemodel.gamecommand to com.fasterxml.jackson.databind;
    opens timmax.tilegame.basemodel.protocol to com.fasterxml.jackson.databind;
}