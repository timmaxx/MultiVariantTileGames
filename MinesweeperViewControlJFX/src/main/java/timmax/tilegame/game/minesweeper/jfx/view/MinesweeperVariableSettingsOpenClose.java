package timmax.tilegame.game.minesweeper.jfx.view;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;

import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsOpenClose;

import static timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsOpenClose.*;

public class MinesweeperVariableSettingsOpenClose extends ViewTextFieldsJfx {
    public MinesweeperVariableSettingsOpenClose( BaseModel baseModel) {
        super( baseModel
                , GameEventMinesweeperVariableParamsOpenClose.class
                , COMMON_LABEL_OF_VARIABLE_PARAMS_OPEN_CLOSE);
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventMinesweeperVariableParamsOpenClose ge = ( ( GameEventMinesweeperVariableParamsOpenClose) gameEvent);
        return  TILES_WERE_OPENED + ge.getTilesWereOpened( ) + ". " +
                TILES_STILL_CLOSED + ge.getTilesStillClosed( ) + ". "
        ;
    }
}