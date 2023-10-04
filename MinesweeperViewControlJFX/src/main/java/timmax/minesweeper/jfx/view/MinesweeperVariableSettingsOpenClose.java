package timmax.minesweeper.jfx.view;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.GameEvent;
import timmax.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsOpenClose;
import timmax.tilegameenginejfx.ViewTextFieldsJfx;

public class MinesweeperVariableSettingsOpenClose extends ViewTextFieldsJfx {
    public MinesweeperVariableSettingsOpenClose( BaseModel baseModel) {
        super( baseModel
                , GameEventMinesweeperVariableParamsOpenClose.class
                , GameEventMinesweeperVariableParamsOpenClose.commonLabel); // ToDo: Разобраться и удалить ведущий '\n' в commonLabel.
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventMinesweeperVariableParamsOpenClose ge = ( ( GameEventMinesweeperVariableParamsOpenClose) gameEvent);
        return  " Tiles were opened = " + ge.getTilesWereOpened( ) + ". " +
                " Tiles are still close = " + ge.getTilesAreStillClose( ) + ". "
        ;
    }
}