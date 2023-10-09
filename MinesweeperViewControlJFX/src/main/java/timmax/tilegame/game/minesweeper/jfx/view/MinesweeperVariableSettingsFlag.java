package timmax.tilegame.game.minesweeper.jfx.view;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;

import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsFlag;

import static timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsFlag.*;

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