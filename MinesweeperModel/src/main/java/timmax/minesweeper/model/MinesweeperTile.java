package timmax.minesweeper.model;

public class MinesweeperTile {
    private boolean isMine;
    private int countMineNeighbors;
    private boolean isOpen;
    private boolean isFlag;

    void setMine( ) {
        isMine = true;
    }

    void incCountMineNeighbors( ) {
        this.countMineNeighbors++;
    }

    boolean isMineForModel( ) {
        return isMine;
    }

    public int getCountMineNeighbors( ) {
        if ( !isOpen) {
            throw new RuntimeException( "It is a secret! (getCountMineNeighbors)");
        }
        return countMineNeighbors;
    }

    public boolean isOpen( ) {
        return isOpen;
    }

    public boolean isMine( ) {
        if ( !isOpen) {
            throw new RuntimeException( "It is a secret! (isMine)");
        }
        return isMine;
    }

    public void open( ) {
        isOpen = true;
    }

    public boolean isFlag( ) {
        if ( isOpen) {
            throw new RuntimeException( "Tile is open. For open tile flag is absent! (isFlag)");
        }
        return isFlag;
    }

    public void setFlag( boolean flag) {
        if ( isOpen) {
            throw new RuntimeException( "Tile is open. For open tile flag is absent! (setFlag)");
        }
        isFlag = flag;
    }
}