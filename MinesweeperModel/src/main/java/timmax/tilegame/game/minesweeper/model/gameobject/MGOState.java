package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.GameObjectState;

public interface MGOState extends GameObjectState {
    int getOneOrZeroMines();

    void open();

    void inverseFlag();
}
