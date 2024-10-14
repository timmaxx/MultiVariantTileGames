package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.IGameObjectState;

public interface IMGOState extends IGameObjectState {
    int getOneOrZeroMines();

    void open();

    void inverseFlag();
}
