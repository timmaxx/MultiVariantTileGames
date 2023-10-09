module timmax.minesweeper.model {
    requires timmax.basetilemodel;
    requires spring.context;
    requires org.slf4j;
    requires java.compiler;
    requires java.annotation;

    exports timmax.tilegame.game.minesweeper.model;
    exports timmax.tilegame.game.minesweeper.model.gameevent;
}