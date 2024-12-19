package timmax.tilegame.sokoban.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.transport.ISenderOfEventOfClient;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfSteps;

import static timmax.tilegame.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfSteps.COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_STEPS;

public class SokobanVariableSettingsCountOfSteps extends ViewTextFieldsJfx {
    public SokobanVariableSettingsCountOfSteps(ISenderOfEventOfClient senderOfEventOfClient, String viewName, GameType gameType) {
        super(senderOfEventOfClient
                , GameEventSokobanVariableParamsCountOfSteps.class
                , COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_STEPS
                , viewName
                , gameType);
    }

    @Override
    protected String createStringFromGameEvent(GameEvent gameEvent) {
        GameEventSokobanVariableParamsCountOfSteps ge = ((GameEventSokobanVariableParamsCountOfSteps) gameEvent);
        return ge.getCountOfSteps() + ". ";
    }
}
