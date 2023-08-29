package timmax.minesweeper.model;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import timmax.basetilemodel.BaseModel;
import timmax.minesweeper.model.gameobject.AllMinesweeperObjects;
import timmax.minesweeper.model.gameobject.MinesweeperTile;

import static org.slf4j.LoggerFactory.getLogger;

// Модель игры Сапёр
@Component
public class MinesweeperModel extends BaseModel {
    private static final Logger log = getLogger(MinesweeperModel.class);
    AllMinesweeperObjects allMinesweeperObjects;

    private final LevelGenerator levelGenerator = new LevelGenerator();


    public void createNewGame(int width, int height, int restOfMineInstallationInPercents) {
        allMinesweeperObjects = levelGenerator.getLevel(width, height, restOfMineInstallationInPercents);
        super.createNewGame(width, height);
    }

    public void inverseFlag(int x, int y) {
        MinesweeperTile minesweeperTile = allMinesweeperObjects.getTileByXY(x, y);
        allMinesweeperObjects.inverseFlag(minesweeperTile);
        notifyViews();
    }

    public void open(int x, int y) {
        MinesweeperTile minesweeperTile = allMinesweeperObjects.getTileByXY(x, y);
        gameStatus = allMinesweeperObjects.open(minesweeperTile);
        notifyViews();
    }

    public boolean getMinesweeperObjectIsOpen(int x, int y) {
        MinesweeperTile minesweeperTile = allMinesweeperObjects.getTileByXY(x, y);
        return minesweeperTile.isOpen();
    }

    public boolean getMinesweeperObjectIsMine(int x, int y) {
        MinesweeperTile minesweeperTile = allMinesweeperObjects.getTileByXY(x, y);
        return minesweeperTile.isMine();
    }

    public boolean getMinesweeperObjectIsFlag(int x, int y) {
        MinesweeperTile minesweeperTile = allMinesweeperObjects.getTileByXY(x, y);
        return minesweeperTile.isFlag();
    }

    public int getCountOfMineNeighbors(int x, int y) {
        MinesweeperTile minesweeperTile = allMinesweeperObjects.getTileByXY(x, y);
        return minesweeperTile.getCountOfMineNeighbors();
    }
}