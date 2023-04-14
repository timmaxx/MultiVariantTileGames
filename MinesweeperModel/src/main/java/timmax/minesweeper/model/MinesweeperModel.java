package timmax.minesweeper.model;

import timmax.basetilemodel.*;
import timmax.minesweeper.model.gameobject.MinesweeperGameObjects;
import timmax.minesweeper.model.gameobject.MinesweeperObject;

public class MinesweeperModel extends BaseModel {
    MinesweeperGameObjects minesweeperGameObjects;

    private final LevelGenerator levelGenerator = new LevelGenerator( );


    public void createNewGame( int width, int height, int restOfMineInstallationInPercents) {
        minesweeperGameObjects = levelGenerator.getLevel( width, height, restOfMineInstallationInPercents);
        super.createNewGame( width, height);
    }

    public void inverseFlag( int x, int y) {
        minesweeperGameObjects.inverseFlag( x, y);
        notifyViews( );
    }

    public void open( int x, int y) {
        gameStatus = minesweeperGameObjects.open( x, y);
        notifyViews( );
    }

    public MinesweeperObject getMinesweeperObject( int x, int y) {
        return minesweeperGameObjects.getMinesweeperObject( x, y);
    }
}