package timmax.sokoban.model;

public class CurrentLevel {
    private int value;

    private static final int countOfLevels = 60; // 2;
    private boolean isCurrentLevelChanged;

    void setValue(int newLevel) {
        if ( value != newLevel || newLevel == 0) {
            value = ( newLevel + countOfLevels ) % countOfLevels;
            isCurrentLevelChanged = true;
        }
    }

    void incValue() {
        value = ( value + 1 + countOfLevels ) % countOfLevels;
        isCurrentLevelChanged = true;
    }

    void decValue() {
        value = ( value - 1 + countOfLevels ) % countOfLevels;
        isCurrentLevelChanged = true;
    }

    int getValue() {
        return value;
    }

    public boolean isCurrentLevelChanged() {
        return isCurrentLevelChanged;
    }
}