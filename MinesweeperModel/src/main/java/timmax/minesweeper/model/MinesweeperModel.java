package timmax.minesweeper.model;

import org.slf4j.Logger;
import timmax.basetilemodel.BaseModel;
import timmax.minesweeper.model.gameobject.AllMinesweeperObjects;
import timmax.minesweeper.model.gameobject.LevelGenerator;

import static org.slf4j.LoggerFactory.getLogger;

// Модель игры Сапёр
public class MinesweeperModel extends BaseModel {
    private static final Logger log = getLogger( MinesweeperModel.class);
    private AllMinesweeperObjects allMinesweeperObjects;

    private final LevelGenerator levelGenerator = new LevelGenerator( );


    public void createNewGame( int width, int height, int restOfMineInstallationInPercents) {
        allMinesweeperObjects = levelGenerator.getLevel( width, height, restOfMineInstallationInPercents);
        super.createNewGame( width, height);
    }

    public void inverseFlag( int x, int y) {
        allMinesweeperObjects.inverseFlag( allMinesweeperObjects.getTileByXY( x, y));
        notifyViews( );
    }

    public void open( int x, int y) {
        gameStatus = allMinesweeperObjects.open( allMinesweeperObjects.getTileByXY( x, y));
        notifyViews( );
    }

    public boolean getMinesweeperTileIsOpen( int x, int y) {
        return allMinesweeperObjects.getTileByXY( x, y).isOpen( );
    }

    public boolean getMinesweeperTileIsMine( int x, int y) {
        return allMinesweeperObjects.getTileByXY( x, y).isMine( );
    }

    public boolean getMinesweeperTileIsFlag( int x, int y) {
        return allMinesweeperObjects.getTileByXY( x, y).isFlag( );
    }

    public int getCountOfMineNeighbors( int x, int y) {
        return allMinesweeperObjects.getTileByXY( x, y).getCountOfMineNeighbors( );
    }
}