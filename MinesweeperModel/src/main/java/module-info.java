module timmax.tilegame.game.minesweeper.model {
    requires java.compiler;
    requires java.annotation;
    requires org.slf4j;
    requires timmax.tilegame.basetilemodel;
    requires com.fasterxml.jackson.annotation;

    exports timmax.tilegame.game.minesweeper.model;
    exports timmax.tilegame.game.minesweeper.model.gameevent;
    exports timmax.tilegame.game.minesweeper.model.gamecommand;

    opens timmax.tilegame.game.minesweeper.model.gameevent to com.fasterxml.jackson.databind;
    opens timmax.tilegame.game.minesweeper.model.gamecommand to com.fasterxml.jackson.databind;
}