package timmax.tilegame.basemodel;

import timmax.tilegame.baseview.View;

public interface BaseModel {
    void createNewGame();

    void addView(View view);

    void restart();

    void nextLevel();

    void prevLevel();

    void win();
}