package timmax.minesweeper.model.gameevent;

import timmax.basetilemodel.gameevent.GameEvent;

public class GameEventMinesweeperVariableParams extends GameEvent {
    private final int tilesWereOpened;
    private final int tilesAreStillClose;
    private final int flagsWereUsed;
    private final int flagsAreStillAvailableForUsing;


    public GameEventMinesweeperVariableParams( int tilesWereOpened, int tilesAreStillClose, int flagsWereUsed, int flagsAreStillAvailableForUsing) {
        this.tilesWereOpened = tilesWereOpened;
        this.tilesAreStillClose = tilesAreStillClose;
        this.flagsWereUsed = flagsWereUsed;
        this.flagsAreStillAvailableForUsing = flagsAreStillAvailableForUsing;
    }

    public int getTilesWereOpened( ) {
        return tilesWereOpened;
    }

    public int getTilesAreStillClose( ) {
        return tilesAreStillClose;
    }

    public int getFlagsWereUsed( ) {
        return flagsWereUsed;
    }

    public int getFlagsAreStillAvailableForUsing( ) {
        return flagsAreStillAvailableForUsing;
    }
}