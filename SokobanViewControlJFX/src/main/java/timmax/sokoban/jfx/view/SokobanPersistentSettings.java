package timmax.sokoban.jfx.view;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.sokoban.model.gameevent.GameEventSokobanPersistentParams;
import timmax.tilegameenginejfx.*;

import static timmax.sokoban.model.gameevent.GameEventSokobanPersistentParams.*;

public class SokobanPersistentSettings extends ViewTextFieldsJfx {
    public SokobanPersistentSettings( BaseModel baseModel) {
        super( baseModel
                , GameEventSokobanPersistentParams.class
                , COMMON_LABEL_OF_PERSISTENT_PARAMS);
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventSokobanPersistentParams ge = ( ( GameEventSokobanPersistentParams) gameEvent);
        return COUNT_OF_BOXES_AND_HOMES + ge.getCountOfBoxesAndHomes( ) + ".";
    }
}