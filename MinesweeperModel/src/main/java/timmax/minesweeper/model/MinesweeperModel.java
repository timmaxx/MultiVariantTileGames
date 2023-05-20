package timmax.minesweeper.model;

import org.springframework.stereotype.Component;
import timmax.basetilemodel.*;
import timmax.minesweeper.model.gameobject.AllMinesweeperObjects;

@Component
public class MinesweeperModel extends BaseModel {
    AllMinesweeperObjects allMinesweeperObjects;

    private final LevelGenerator levelGenerator = new LevelGenerator();


    public void createNewGame(int width, int height, int restOfMineInstallationInPercents) {
        allMinesweeperObjects = levelGenerator.getLevel(width, height, restOfMineInstallationInPercents);
        super.createNewGame(width, height);
    }

    public void inverseFlag(int x, int y) {
        allMinesweeperObjects.inverseFlag(x, y);
        notifyViews();
    }

    public void open(int x, int y) {
        gameStatus = allMinesweeperObjects.open(x, y);
        notifyViews();
    }

    public boolean getMinesweeperObjectIsOpen(int x, int y) {
        return allMinesweeperObjects.getMinesweeperObjectIsOpen(x, y);
    }

    public boolean getMinesweeperObjectIsMine(int x, int y) {
        return allMinesweeperObjects.getMinesweeperObjectIsMine(x, y);
    }

    public boolean getMinesweeperObjectIsFlag(int x, int y) {
        return allMinesweeperObjects.getMinesweeperObjectIsFlag(x, y);
    }

    public int getCountOfMineNeighbors(int x, int y) {
        return allMinesweeperObjects.getCountOfMineNeighbors(x, y);
    }
}