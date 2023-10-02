package timmax.sokoban.jfx.view;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.GameEvent;
import timmax.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses;
import timmax.tilegameenginejfx.ViewTextFieldsJfx;

public class SokobanVariableSettingsCountOfBoxesInHouses extends ViewTextFieldsJfx {
    public SokobanVariableSettingsCountOfBoxesInHouses( BaseModel baseModel) {
        super( baseModel
                , GameEventSokobanVariableParamsCountOfBoxesInHouses.class
                , "\nVariable settings - Count of boxes in houses = "); // ToDo: Разобраться и удалить ведущий '\n'
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventSokobanVariableParamsCountOfBoxesInHouses ge = ( ( GameEventSokobanVariableParamsCountOfBoxesInHouses) gameEvent);
        return ge.getCountOfBoxesInHouses( ) + ". ";
    }
}