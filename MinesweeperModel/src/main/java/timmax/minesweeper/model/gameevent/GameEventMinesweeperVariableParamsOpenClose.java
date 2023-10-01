package timmax.minesweeper.model.gameevent;

import timmax.basetilemodel.gameevent.GameEvent;

public class GameEventMinesweeperVariableParamsOpenClose extends GameEvent {
    private final int tilesWereOpened;
    private final int tilesAreStillClose;


    public GameEventMinesweeperVariableParamsOpenClose(int tilesWereOpened, int tilesAreStillClose) {
        this.tilesWereOpened = tilesWereOpened;
        this.tilesAreStillClose = tilesAreStillClose;
    }

    public int getTilesWereOpened( ) {
        return tilesWereOpened;
    }

    public int getTilesAreStillClose( ) {
        return tilesAreStillClose;
    }

}