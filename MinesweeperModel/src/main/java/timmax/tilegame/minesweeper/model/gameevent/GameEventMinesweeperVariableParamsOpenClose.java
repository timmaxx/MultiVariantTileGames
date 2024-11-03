package timmax.tilegame.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventMinesweeperVariableParamsOpenClose extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n';
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_OPEN_CLOSE = "\nVariable settings - open and close tiles:\n";
    public final static String TILES_WERE_OPENED = " Tiles were opened = ";
    public final static String TILES_STILL_CLOSED = " Tiles still closed = ";

    private int tilesWereOpened;
    private int tilesStillClose;

    public GameEventMinesweeperVariableParamsOpenClose() {
    }
/*
    public GameEventMinesweeperVariableParamsOpenClose(
            int tilesWereOpened,
            int tilesStillClose) {
        this.tilesWereOpened = tilesWereOpened;
        this.tilesStillClose = tilesStillClose;
    }
*/
    public int getTilesWereOpened() {
        return tilesWereOpened;
    }

    public int getTilesStillClosed() {
        return tilesStillClose;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(tilesWereOpened);
        out.writeInt(tilesStillClose);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        tilesWereOpened = in.readInt();
        tilesStillClose = in.readInt();
    }
}
