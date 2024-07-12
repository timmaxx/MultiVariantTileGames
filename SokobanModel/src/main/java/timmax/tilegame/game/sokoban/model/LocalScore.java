package timmax.tilegame.game.sokoban.model;

public class LocalScore {
    private int value;
    private boolean isLocalScoreChanged;

    void setValue(int newScore) {
        if (value != newScore) {
            value = newScore;
            isLocalScoreChanged = true;
        }
    }

    void incValue() {
        value++;
        isLocalScoreChanged = true;
    }

    void decValue() {
        value--;
        isLocalScoreChanged = true;
    }

    int getValue() {
        return value;
    }

    boolean isLocalScoreChanged() {
        return isLocalScoreChanged;
    }
}
