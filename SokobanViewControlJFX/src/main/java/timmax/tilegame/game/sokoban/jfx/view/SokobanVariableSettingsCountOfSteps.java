package timmax.tilegame.game.sokoban.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.client.IModelOfClient;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfSteps;

import static timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfSteps.COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_STEPS;

public class SokobanVariableSettingsCountOfSteps extends ViewTextFieldsJfx {
    public SokobanVariableSettingsCountOfSteps(IModelOfClient iModelOfClient, String viewName) {
        super(iModelOfClient
                , GameEventSokobanVariableParamsCountOfSteps.class
                , COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_STEPS
                , viewName);
    }

    @Override
    protected String createStringFromGameEvent(GameEvent gameEvent) {
        GameEventSokobanVariableParamsCountOfSteps ge = ((GameEventSokobanVariableParamsCountOfSteps) gameEvent);
        return ge.getCountOfSteps() + ". ";
    }
}
