module timmax.tilegame.game.minesweeper.model {
    requires java.compiler;
    requires java.annotation;
    requires org.slf4j;
    requires timmax.tilegame.basetilemodel;
    requires timmax.tilegame.game.sokoban.model;

    exports timmax.tilegame.game.minesweeper.model;
    exports timmax.tilegame.game.minesweeper.model.gameevent;
    exports timmax.tilegame.game.minesweeper.model.gamecommand;
}