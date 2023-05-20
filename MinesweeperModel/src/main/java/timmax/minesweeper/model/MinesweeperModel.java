package timmax.minesweeper.model;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import timmax.basetilemodel.BaseModel;
import timmax.minesweeper.model.gameobject.AllMinesweeperObjects;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.slf4j.LoggerFactory.getLogger;

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

    @PostConstruct
    public void doMyInit( ) {
        System.out.println( "Doing my initialization. " + this);
        log.debug("Doing my initialization. " + this);
    }

    @PreDestroy
    public void doMyDestroy( ) {
        System.out.println( "Doing my destruction. " + this);
        log.debug( "Doing my destruction. " + this);
    }
}