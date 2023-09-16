package timmax.minesweeper.model.gameevent;


public class GameEventOneTileNoMine extends GameEventOneTile {
    private final int CountOfMineNeighbors;

    public GameEventOneTileNoMine(int x, int y, int CountOfMineNeighbors) {
        super( x, y);
        this.CountOfMineNeighbors = CountOfMineNeighbors;
    }

    public int getCountOfMineNeighbors( ) {
        return CountOfMineNeighbors;
    }
}