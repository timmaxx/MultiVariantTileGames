module timmax.tilegame.game.minesweeper.model {
    requires java.compiler;
    requires java.annotation;
    requires org.slf4j;
    requires timmax.tilegame.basetilemodel;

    exports timmax.tilegame.game.minesweeper.model;
    exports timmax.tilegame.game.minesweeper.model.gameevent;
}