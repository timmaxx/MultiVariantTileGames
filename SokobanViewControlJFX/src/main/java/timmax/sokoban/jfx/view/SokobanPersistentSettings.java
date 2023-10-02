package timmax.sokoban.jfx.view;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.*;
import timmax.sokoban.model.gameevent.GameEventSokobanPersistentParams;
import timmax.tilegameenginejfx.*;

public class SokobanPersistentSettings extends ViewTextFieldsJfx {
    public SokobanPersistentSettings( BaseModel baseModel) {
        super( baseModel
                , GameEventSokobanPersistentParams.class
                , "\nPersistent settings for Sokoban\n"); // ToDo: Разобраться и удалить ведущий '\n'
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventSokobanPersistentParams ge = ( ( GameEventSokobanPersistentParams) gameEvent);
        return " Count of all boxes and homes = " + ge.getCountOfBoxesAndHomes( ) + ".";
    }
}