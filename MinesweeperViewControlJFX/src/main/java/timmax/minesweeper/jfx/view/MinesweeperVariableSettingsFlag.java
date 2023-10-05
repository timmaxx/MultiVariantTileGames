package timmax.minesweeper.jfx.view;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.GameEvent;
import timmax.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsFlag;
import timmax.tilegameenginejfx.ViewTextFieldsJfx;

import static timmax.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsFlag.*;

public class MinesweeperVariableSettingsFlag extends ViewTextFieldsJfx {
    public MinesweeperVariableSettingsFlag( BaseModel baseModel) {
        super( baseModel
                , GameEventMinesweeperVariableParamsFlag.class
                , COMMON_LABEL_OF_VARIABLE_PARAMS_FLAG);
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventMinesweeperVariableParamsFlag ge = ( ( GameEventMinesweeperVariableParamsFlag) gameEvent);
        return  FLAGS_WERE_USED + ge.getFlagsWereUsed( ) + ". " +
                FLAGS_ARE_STILL_AVAILABLE_FOR_USING + ge.getFlagsAreStillAvailableForUsing( ) + ". "
        ;
    }
}