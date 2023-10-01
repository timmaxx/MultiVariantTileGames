package timmax.minesweeper.model.gameevent;

import timmax.basetilemodel.gameevent.GameEvent;

public class GameEventMinesweeperVariableParamsFlag extends GameEvent {
    private final int flagsWereUsed;
    private final int flagsAreStillAvailableForUsing;


    public GameEventMinesweeperVariableParamsFlag( int flagsWereUsed, int flagsAreStillAvailableForUsing) {
        this.flagsWereUsed = flagsWereUsed;
        this.flagsAreStillAvailableForUsing = flagsAreStillAvailableForUsing;
    }

    public int getFlagsWereUsed( ) {
        return flagsWereUsed;
    }

    public int getFlagsAreStillAvailableForUsing( ) {
        return flagsAreStillAvailableForUsing;
    }
}