package timmax.tilegame.game.minesweeper.model.gameevent;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

public class GameEventOneTileOpenNoMine extends GameEventOneTile {
    private final int countOfMineNeighbors;


    public GameEventOneTileOpenNoMine(
            int x,
            int y,
            int countOfMineNeighbors) {
        super(x, y);
        this.countOfMineNeighbors = countOfMineNeighbors;
    }

    public int getCountOfMineNeighbors() {
        return countOfMineNeighbors;
    }
}