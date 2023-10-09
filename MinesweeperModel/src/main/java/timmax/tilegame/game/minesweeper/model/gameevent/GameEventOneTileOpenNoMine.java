package timmax.tilegame.game.minesweeper.model.gameevent;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

public class GameEventOneTileOpenNoMine extends GameEventOneTile {
    private final int CountOfMineNeighbors;


    public GameEventOneTileOpenNoMine( int x, int y, int CountOfMineNeighbors) {
        super( x, y);
        this.CountOfMineNeighbors = CountOfMineNeighbors;
    }

    public int getCountOfMineNeighbors( ) {
        return CountOfMineNeighbors;
    }
}