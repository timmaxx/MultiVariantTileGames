package timmax.minesweeper.jfx.view;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.GameEvent;
import timmax.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams;
import timmax.tilegameenginejfx.*;

public class MinesweeperPersistentSettings extends ViewTextFieldsJfx {
    public MinesweeperPersistentSettings( BaseModel baseModel) {
        super( baseModel
                , GameEventMinesweeperPersistentParams.class
                , GameEventMinesweeperPersistentParams.commonLabel); // ToDo: Разобраться и удалить ведущий '\n' в commonLabel.
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventMinesweeperPersistentParams ge = ( ( GameEventMinesweeperPersistentParams) gameEvent);
        return " Count of all mines in the field = " + ge.getCountOfMines( ) + ".";
    }
}