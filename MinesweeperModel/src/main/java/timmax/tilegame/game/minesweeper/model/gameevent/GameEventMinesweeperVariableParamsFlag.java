package timmax.tilegame.game.minesweeper.model.gameevent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import timmax.tilegame.basemodel.gameevent.GameEventROTextFields;

import static com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES;

public class GameEventMinesweeperVariableParamsFlag extends GameEventROTextFields {
    // ToDo: Разобраться и удалить ведущий '\n';
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_FLAG = "\nVariable settings - flags:\n";
    public final static String FLAGS_WERE_USED = " Flags were used = ";
    public final static String FLAGS_ARE_STILL_AVAILABLE_FOR_USING = " Flags are still available for using = ";

    private final int flagsWereUsed;
    private final int flagsAreStillAvailableForUsing;

    @JsonCreator(mode = PROPERTIES)
    public GameEventMinesweeperVariableParamsFlag(
            @JsonProperty( "flagsWereUsed") int flagsWereUsed,
            @JsonProperty( "flagsAreStillAvailableForUsing") int flagsAreStillAvailableForUsing) {
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