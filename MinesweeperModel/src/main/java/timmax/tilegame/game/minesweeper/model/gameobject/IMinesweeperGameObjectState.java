package timmax.tilegame.game.minesweeper.model.gameobject;

import timmax.tilegame.basemodel.gameobject.IOneTileGameObjectState;

public interface IMinesweeperGameObjectState extends IOneTileGameObjectState {
    int getOneOrZeroMines();

    void open();

    void inverseFlag();

    // int getCountOfNeighboursWithMines();
}
