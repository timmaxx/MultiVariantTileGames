package timmax.tilegame.game.sokoban.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfClient;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfSteps;

import static timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfSteps.COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_STEPS;

public class SokobanVariableSettingsCountOfSteps<ClientId> extends ViewTextFieldsJfx<ClientId> {
    public SokobanVariableSettingsCountOfSteps(TransportOfClient<ClientId> transportOfClient, String viewName) {
        super(transportOfClient
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
