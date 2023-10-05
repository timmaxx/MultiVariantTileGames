package timmax.minesweeper.model.gameevent;

import timmax.basetilemodel.gameevent.GameEventROTextFields;

public class GameEventMinesweeperVariableParamsOpenClose extends GameEventROTextFields {
    // ToDo: Разобраться и удалить ведущий '\n';
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_OPEN_CLOSE = "\nVariable settings - open and close tiles:\n";

    public final static String TILES_WERE_OPENED = " Tiles were opened = ";
    public final static String TILES_STILL_CLOSED = " Tiles are still closed = ";

    private final int tilesWereOpened;
    private final int tilesAreStillClose;


    public GameEventMinesweeperVariableParamsOpenClose( int tilesWereOpened, int tilesAreStillClose) {
        this.tilesWereOpened = tilesWereOpened;
        this.tilesAreStillClose = tilesAreStillClose;
    }

    public int getTilesWereOpened( ) {
        return tilesWereOpened;
    }

    public int getTilesStillClosed( ) {
        return tilesAreStillClose;
    }

}