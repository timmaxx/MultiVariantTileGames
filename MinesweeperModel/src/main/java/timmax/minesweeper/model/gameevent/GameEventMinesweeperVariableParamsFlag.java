package timmax.minesweeper.model.gameevent;

import timmax.tilegame.basemodel.gameevent.GameEventROTextFields;

public class GameEventMinesweeperVariableParamsFlag extends GameEventROTextFields {
    // ToDo: Разобраться и удалить ведущий '\n';
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_FLAG = "\nVariable settings - flags:\n";

    public final static String FLAGS_WERE_USED = " Flags were used = ";
    public final static String FLAGS_ARE_STILL_AVAILABLE_FOR_USING = " Flags are still available for using = ";

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