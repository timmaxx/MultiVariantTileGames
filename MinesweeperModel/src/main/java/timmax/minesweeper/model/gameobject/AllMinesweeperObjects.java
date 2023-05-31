package timmax.minesweeper.model.gameobject;

import timmax.basetilemodel.GameStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static timmax.basetilemodel.GameStatus.*;

public class AllMinesweeperObjects {
    private final int width;
    private final int height;
    private final int countOfMinesAndHomes;

    private int countOfFlags;

    private int countOfClosedTiles;

    private final MinesweeperObject[ ][ ] minesweeperObjects;

    public AllMinesweeperObjects( int width, int height
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

    public MinesweeperObject[][] getMinesweeperObjects() {
        return minesweeperObjects;
    }

    public List<MinesweeperObject> getListOfMinesweeperObjects() {
        List<MinesweeperObject> minesweeperObjectList = new ArrayList<>();
        minesweeperObjectList.add(new MinesweeperObject(0, 0, true));
        minesweeperObjectList.add(new MinesweeperObject(0, 1, true));
        minesweeperObjectList.add(new MinesweeperObject(1, 0, false));
        minesweeperObjectList.add(new MinesweeperObject(1, 1, true));
        return minesweeperObjectList;
    }

    public List<List<MinesweeperObject>> getListOfListOfMinesweeperObjects() {
        List<List<MinesweeperObject>> minesweeperObjectListOfList = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<MinesweeperObject> minesweeperObjectList = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                minesweeperObjectList.add( minesweeperObjects[y][x]);
            }
            minesweeperObjectListOfList.add(minesweeperObjectList);
        }
        return  minesweeperObjectListOfList;
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