package timmax.sokoban.jfx.view;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.GameEvent;
import timmax.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses;
import timmax.tilegameenginejfx.ViewTextFieldsJfx;

import static timmax.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses.COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_BOXES_IN_HOMES;

public class SokobanVariableSettingsCountOfBoxesInHouses extends ViewTextFieldsJfx {
    public SokobanVariableSettingsCountOfBoxesInHouses( BaseModel baseModel) {
        super( baseModel
                , GameEventSokobanVariableParamsCountOfBoxesInHouses.class
                , COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_BOXES_IN_HOMES);
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventSokobanVariableParamsCountOfBoxesInHouses ge = ( ( GameEventSokobanVariableParamsCountOfBoxesInHouses) gameEvent);
        return ge.getCountOfBoxesInHouses( ) + ". ";
    }
}