package timmax.minesweeper.model.gameobject;

import timmax.basetilemodel.XY;
import java.util.Set;

public class MinesweeperObject extends XY {
    private Set< MinesweeperObject> neighbors;
    private boolean isOpen;
    private final boolean isMine;
    private boolean isFlag;
    private int countOfMineNeighbors;

    public MinesweeperObject( int x, int y, boolean isMine) {
        super( x, y);
        this.isMine = isMine;
        countOfMineNeighbors = -1;
    }

    public boolean isOpen( ) {
        return isOpen;
    }

    public int getCountOfMineNeighbors( ) {
        if ( !isOpen) {
            throw new RuntimeException( "It is a secret! (getCountMineNeighbors)");
        }
        if ( countOfMineNeighbors >= 0) {
            return countOfMineNeighbors;
        }
        countOfMineNeighbors = 0;
        for ( MinesweeperObject minesweeperObject: getNeighbors()) {
            if ( minesweeperObject.isMine) {
                countOfMineNeighbors++;
            }
        }
        return countOfMineNeighbors;
    }

    public boolean isFlag( ) {
        if ( isOpen( )) {
            throw new RuntimeException( "Tile is open. For open tile flag is absent! (isFlag)");
        }
        return isFlag;
    }

    public boolean isMine( ) {
        if ( !isOpen( )) {
            throw new RuntimeException( "It is a secret! (isMine)");
        }
        return isMine;
    }


    // Package --------------------------------------------------------------------------------------------------------
    boolean isMineInPackageMode( ) {
        return isMine;
    }

    void open( ) {
        isOpen = true;
    }

    void inverseFlag( ) {
        if ( isOpen( )) {
            throw new RuntimeException( "Tile is open. For open tile flag is absent! (inverseFlag)");
        }
        this.isFlag = !isFlag;
    }

    Set< MinesweeperObject> getNeighbors() {
        return neighbors;
    }

    void setNeighbors( Set< MinesweeperObject> neighbors) {
        this.neighbors = neighbors;
    }
}