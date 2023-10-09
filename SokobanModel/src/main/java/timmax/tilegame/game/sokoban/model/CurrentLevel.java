package timmax.tilegame.game.sokoban.model;

public class CurrentLevel {
    private static final int COUNT_OF_LEVELS = 4; // 60; // 2;

    private int value = 1;


    void incValue( ) {
        value = ( value + COUNT_OF_LEVELS) % COUNT_OF_LEVELS + 1;
    }

    void decValue( ) {
        value = ( value - 2 + COUNT_OF_LEVELS) % COUNT_OF_LEVELS + 1;
    }

    int getValue( ) {
        return value;
    }
}