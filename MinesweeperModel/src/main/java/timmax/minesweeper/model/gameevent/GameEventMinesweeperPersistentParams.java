package timmax.minesweeper.model.gameevent;

import timmax.basetilemodel.gameevent.GameEvent;

public class GameEventMinesweeperPersistentParams extends GameEvent {
    private final int countOfMines;

    public GameEventMinesweeperPersistentParams( int countOfMines) {
        this.countOfMines = countOfMines;
    }

    public int getCountOfMines( ) {
        return countOfMines;
    }
}