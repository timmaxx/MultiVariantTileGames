package timmax.minesweeper.model;

import timmax.basetilemodel.*;
import timmax.minesweeper.model.gameobject.MinesweeperGameObjects;

public class MinesweeperModel extends BaseModel {
    MinesweeperGameObjects minesweeperGameObjects;

    private final LevelGenerator levelGenerator = new LevelGenerator();


    public void createNewGame(int width, int height, int restOfMineInstallationInPercents) {
        minesweeperGameObjects = levelGenerator.getLevel(width, height, restOfMineInstallationInPercents);
        super.createNewGame(width, height);
    }

    public void inverseFlag(int x, int y) {
        minesweeperGameObjects.inverseFlag(x, y);
        notifyViews();
    }

    public void open(int x, int y) {
        gameStatus = minesweeperGameObjects.open(x, y);
        notifyViews();
    }

    public boolean getMinesweeperObjectIsOpen(int x, int y) {
        return minesweeperGameObjects.getMinesweeperObjectIsOpen(x, y);
    }

    public boolean getMinesweeperObjectIsMine(int x, int y) {
        return minesweeperGameObjects.getMinesweeperObjectIsMine(x, y);
    }

    public boolean getMinesweeperObjectIsFlag(int x, int y) {
        return minesweeperGameObjects.getMinesweeperObjectIsFlag(x, y);
    }

    public int getCountOfMineNeighbors(int x, int y) {
        return minesweeperGameObjects.getCountOfMineNeighbors( x, y);
    }
}