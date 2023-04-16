package timmax.minesweeper.model.gameobject;

import timmax.basetilemodel.GameStatus;

import java.util.HashSet;
import java.util.Set;

import static timmax.basetilemodel.GameStatus.*;

public class MinesweeperGameObjects {
    private final int width;
    private final int height;
    private final int countOfMinesAndHomes;

    private int countOfFlags;

    private int countOfClosedTiles;

    private final MinesweeperObject[ ][ ] minesweeperObjects;

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
    }

    public GameStatus open( int x, int y) {
        MinesweeperObject minesweeperObject =  minesweeperObjects[ y][ x];
        if ( !minesweeperObject.isOpen( ) && minesweeperObject.isFlag( )) {
            return GAME;
        }
        return openRecursive( minesweeperObject);
    }


    // Private --------------------------------------------------------------------------------------------------------
    private GameStatus openRecursive( MinesweeperObject minesweeperObject) {
        minesweeperObject.open( );
        countOfClosedTiles--;
        if ( minesweeperObject.isMine( )) {
            return DEFEAT;
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
            return VICTORY;
        }
        return GAME;
    }

    private void defineNeighbors( int x, int y) {
        MinesweeperObject minesweeperObject = minesweeperObjects[ y][ x];
        if ( minesweeperObject.getNeighbors( ) != null ) {
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

    public boolean getMinesweeperObjectIsOpen(int x, int y) {
        MinesweeperObject minesweeperObject = minesweeperObjects[ y][ x];
        return minesweeperObject.isOpen();
    }

    public boolean getMinesweeperObjectIsMine(int x, int y) {
        MinesweeperObject minesweeperObject = minesweeperObjects[ y][ x];
        return minesweeperObject.isMine();
    }

    public boolean getMinesweeperObjectIsFlag(int x, int y) {
        MinesweeperObject minesweeperObject = minesweeperObjects[ y][ x];
        return minesweeperObject.isFlag();
    }

    public int getCountOfMineNeighbors(int x, int y) {
        MinesweeperObject minesweeperObject = minesweeperObjects[ y][ x];
        return minesweeperObject.getCountOfMineNeighbors( );
    }
}