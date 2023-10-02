package timmax.minesweeper.jfx.view;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.GameEvent;
import timmax.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsFlag;
import timmax.tilegameenginejfx.ViewTextFieldsJfx;

public class MinesweeperVariableSettingsFlag extends ViewTextFieldsJfx {
    public MinesweeperVariableSettingsFlag( BaseModel baseModel) {
        super( baseModel
                , GameEventMinesweeperVariableParamsFlag.class
                , "\nVariable settings - flags:\n"); // ToDo: Разобраться и удалить ведущий '\n' в commonLabel.
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventMinesweeperVariableParamsFlag ge = ( ( GameEventMinesweeperVariableParamsFlag) gameEvent);
        return  " Flags were used = " + ge.getFlagsWereUsed( ) + ". " +
                " Flags are still available for using = " + ge.getFlagsAreStillAvailableForUsing( ) + ". "
        ;
    }
}