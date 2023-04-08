package timmax.sokoban.model;

public class CurrentLevel {
    private int value = 1;
    private static final int COUNT_OF_LEVELS = 60; // 2;
    private boolean isCurrentLevelChanged;

    void setValue( int newLevel) {
        if ( value != newLevel || newLevel == 0) {
            value = ( newLevel + COUNT_OF_LEVELS) % COUNT_OF_LEVELS;
            isCurrentLevelChanged = true;
        }
    }

    void incValue( ) {
        value = ( value + COUNT_OF_LEVELS) % COUNT_OF_LEVELS + 1;
        isCurrentLevelChanged = true;
    }

    void restart( ) {
        isCurrentLevelChanged = true;
    }

    void decValue( ) {
        value = ( value - 2 + COUNT_OF_LEVELS) % COUNT_OF_LEVELS + 1;
        isCurrentLevelChanged = true;
    }

    int getValue( ) {
        return value;
    }

    public boolean isCurrentLevelChanged( ) {
        return isCurrentLevelChanged;
    }

    public void dropCurrentLevelChanged( ) {
        isCurrentLevelChanged = false;
    }
}