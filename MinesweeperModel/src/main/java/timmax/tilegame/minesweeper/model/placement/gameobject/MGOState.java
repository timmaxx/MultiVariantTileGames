package timmax.tilegame.minesweeper.model.placement.gameobject;

import timmax.tilegame.basemodel.placement.gameobject.GameObjectState;

public interface MGOState extends GameObjectState {
    int getOneOrZeroMines();

    void open();

    void inverseFlag();
}
