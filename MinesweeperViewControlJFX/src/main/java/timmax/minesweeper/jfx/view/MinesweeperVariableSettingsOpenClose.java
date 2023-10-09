package timmax.minesweeper.jfx.view;

import timmax.tilegame.basemodel.ConnectionToBaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsOpenClose;
import timmax.tilegameenginejfx.ViewTextFieldsJfx;

import static timmax.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsOpenClose.*;

public class MinesweeperVariableSettingsOpenClose extends ViewTextFieldsJfx {
    public MinesweeperVariableSettingsOpenClose( ConnectionToBaseModel connectionToBaseModel) {
        super( connectionToBaseModel
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