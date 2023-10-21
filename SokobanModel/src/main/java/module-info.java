module timmax.tilegame.game.sokoban.model {
    requires timmax.tilegame.basetilemodel;
    requires com.fasterxml.jackson.annotation;

    exports timmax.tilegame.game.sokoban.model;
    exports timmax.tilegame.game.sokoban.model.gameobject;
    exports timmax.tilegame.game.sokoban.model.route;
    exports timmax.tilegame.game.sokoban.model.gameevent;
    exports timmax.tilegame.game.sokoban.model.gamecommand;

    opens timmax.tilegame.game.sokoban.model.gameevent to com.fasterxml.jackson.databind;
    opens timmax.tilegame.game.sokoban.model.gamecommand to com.fasterxml.jackson.databind;
}