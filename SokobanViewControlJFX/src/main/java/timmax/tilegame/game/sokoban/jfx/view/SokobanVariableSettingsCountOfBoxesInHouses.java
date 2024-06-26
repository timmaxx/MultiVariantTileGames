package timmax.tilegame.game.sokoban.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfClient;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses;

import static timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanVariableParamsCountOfBoxesInHouses.COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_BOXES_IN_HOMES;

public class SokobanVariableSettingsCountOfBoxesInHouses<ClientId> extends ViewTextFieldsJfx<ClientId> {
    public SokobanVariableSettingsCountOfBoxesInHouses(TransportOfClient<ClientId> transportOfClient, String viewName) {
        super(transportOfClient
                , GameEventSokobanVariableParamsCountOfBoxesInHouses.class
                , COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_BOXES_IN_HOMES
                , viewName
        );
    }

    @Override
    protected String createStringFromGameEvent(GameEvent gameEvent) {
        GameEventSokobanVariableParamsCountOfBoxesInHouses ge = ((GameEventSokobanVariableParamsCountOfBoxesInHouses) gameEvent);
        return ge.getCountOfBoxesInHouses() + ". ";
    }
}
