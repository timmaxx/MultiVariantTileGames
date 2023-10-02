package timmax.sokoban.jfx.view;

import timmax.basetilemodel.BaseModel;
import timmax.basetilemodel.gameevent.*;
import timmax.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfSteps;
import timmax.tilegameenginejfx.ViewTextFieldsJfx;

public class SokobanVariableSettingsCountOfSteps extends ViewTextFieldsJfx {
    public SokobanVariableSettingsCountOfSteps( BaseModel baseModel) {
        super( baseModel
                , GameEventSokobanVariableParamsCountOfSteps.class
                , "\nVariable settings - Count of steps = "); // ToDo: Разобраться и удалить ведущий '\n'
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventSokobanVariableParamsCountOfSteps ge = ( ( GameEventSokobanVariableParamsCountOfSteps) gameEvent);
        return ge.getCountOfSteps( ) + ". ";
    }
}