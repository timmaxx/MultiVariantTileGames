package timmax.tilegame.sokoban.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.transport.ISenderOfEventOfClient;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses;

import static timmax.tilegame.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses.COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_BOXES_IN_HOMES;

public class SokobanVariableSettingsCountOfBoxesInHouses extends ViewTextFieldsJfx {
    public SokobanVariableSettingsCountOfBoxesInHouses(ISenderOfEventOfClient senderOfEventOfClient, String viewName, GameType gameType) {
        super(senderOfEventOfClient
                , GameEventSokobanVariableParamsCountOfBoxesInHouses.class
                , COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_BOXES_IN_HOMES
                , viewName
                , gameType
        );
    }

    @Override
    protected String createStringFromGameEvent(GameEvent gameEvent) {
        GameEventSokobanVariableParamsCountOfBoxesInHouses ge = ((GameEventSokobanVariableParamsCountOfBoxesInHouses) gameEvent);
        return ge.getCountOfBoxesInHouses() + ". ";
    }
}
