package timmax.tilegame.game.minesweeper.model.gameevent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import timmax.tilegame.basemodel.gameevent.GameEventROTextFields;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class GameEventMinesweeperVariableParamsOpenClose extends GameEventROTextFields {
    // ToDo: Разобраться и удалить ведущий '\n';
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_OPEN_CLOSE = "\nVariable settings - open and close tiles:\n";
    public final static String TILES_WERE_OPENED = " Tiles were opened = ";
    public final static String TILES_STILL_CLOSED = " Tiles still closed = ";

    private final int tilesWereOpened;
    private final int tilesStillClose;


    @JsonCreator(mode = PROPERTIES)
    public GameEventMinesweeperVariableParamsOpenClose(
            @JsonProperty( "tilesWereOpened") int tilesWereOpened,
            @JsonProperty( "tilesStillClosed") int tilesStillClose) {
        this.tilesWereOpened = tilesWereOpened;
        this.tilesStillClose = tilesStillClose;
    }

    public int getTilesWereOpened( ) {
        return tilesWereOpened;
    }

    public int getTilesStillClosed( ) {
        return tilesStillClose;
    }
}