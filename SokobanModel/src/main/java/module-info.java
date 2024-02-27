module timmax.tilegame.game.sokoban.model {
    requires javafx.graphics;
    requires timmax.tilegame.basetilemodel;
    requires org.slf4j;

    exports timmax.tilegame.game.sokoban.model;
    exports timmax.tilegame.game.sokoban.model.gameobject;
    exports timmax.tilegame.game.sokoban.model.route;
    exports timmax.tilegame.game.sokoban.model.gameevent;
}
