package timmax.minesweeper.model.gameobject;

import timmax.basetilemodel.GameStatus;

import java.util.HashSet;
import java.util.Set;

public class MinesweeperGameObjects {
    private final int width;
    private final int height;
    private final int countOfMinesAndHomes;

    private int countOfFlags;

    private int countOfClosedTiles;

    private final MinesweeperObject[ ][ ] minesweeperObjects;

    // private GameStatus gameStatus;

    public MinesweeperGameObjects( int width, int height
            , MinesweeperObject[ ][ ] minesweeperObjects
            , int countOfMinesAndHomes) {
        this.width = width;
        this.height = height;
        this.minesweeperObjects = minesweeperObjects;
        this.countOfMinesAndHomes = countOfMinesAndHomes;

        countOfClosedTiles = width * height;
        countOfFlags = countOfMinesAndHomes;
    }
/*
    public int getWidth( ) {
        return width;
    }

    public int getHeight( ) {
        return height;
    }

    public int getCountOfMinesAndHomes( ) {
        return countOfMinesAndHomes;
    }

    public boolean isOpen(int x, int y) {
        return minesweeperObjects[ y][ x].isOpen( );
    }

    public boolean isFlag(int x, int y) {
        return minesweeperObjects[ y][ x].isFlag( );
    }

    public boolean isMine(int x, int y) {
        return minesweeperObjects[ y][ x].isMine( );
    }
*/
    public void inverseFlag(int x, int y) {
        MinesweeperObject minesweeperObject = minesweeperObjects[ y][ x];
        if (        minesweeperObject.isOpen( )
                ||  ( countOfFlags == 0 && !minesweeperObject.isFlag( ))
        //        ||  gameStatus != GameStatus.GAME
        ) {
            return;
        }
        minesweeperObject.inverseFlag( );
        if ( minesweeperObject.isFlag( )) {
            countOfFlags++;
        } else {
            countOfFlags--;
        }
        // notifyViews( );
    }

    public GameStatus open( int x, int y) {
        MinesweeperObject minesweeperObject =  minesweeperObjects[ y][ x];
        return openRecursive( minesweeperObject);
    }

    public MinesweeperObject getMinesweeperObject( int x, int y) {
        return minesweeperObjects[ y][ x];
    }


    // Private --------------------------------------------------------------------------------------------------------
    private GameStatus openRecursive( MinesweeperObject minesweeperObject) {
        minesweeperObject.open( );
        countOfClosedTiles--;
        if ( minesweeperObject.isMine( )) {
            return GameStatus.DEFEAT;
        } else {
            defineNeighbors( minesweeperObject.getX( ), minesweeperObject.getY( ));
            if ( minesweeperObject.getCountOfMineNeighbors( ) == 0) {
                for ( MinesweeperObject minesweeperObjectNeighbor: minesweeperObject.getNeighbors( )) {
                    if ( !minesweeperObjectNeighbor.isOpen( ) && !minesweeperObjectNeighbor.isMineInPackageMode( )) {
                        openRecursive( minesweeperObjectNeighbor);
                    }
                }
            }
        }

        if ( countOfClosedTiles == countOfMinesAndHomes && !minesweeperObject.isMine( )) {
            return GameStatus.VICTORY;
        }
        return GameStatus.GAME;
    }

    private void defineNeighbors(int x, int y) {
        MinesweeperObject minesweeperObject = minesweeperObjects[ y][ x];
        if ( minesweeperObject.getNeighbors() != null ) {
            return;
        }
        Set< MinesweeperObject> neighbors = new HashSet< >( );
        minesweeperObject.setNeighbors( neighbors);
        for ( int yy = y - 1; yy <= y + 1; yy++) {
            for ( int xx = x - 1; xx <= x + 1; xx++) {
                if (        ( yy < 0 || yy >= minesweeperObjects.length)
                        ||  ( xx < 0 || xx >= minesweeperObjects[ y].length)
                        ||  ( xx == x && yy == y)) {
                    continue;
                }
                neighbors.add( minesweeperObjects[ yy][ xx]);
            }
        }
    }
}