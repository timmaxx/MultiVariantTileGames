package timmax.tilegame.game.sokoban.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfClient;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanPersistentParams;

import static timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanPersistentParams.COMMON_LABEL_OF_PERSISTENT_PARAMS;
import static timmax.tilegame.game.sokoban.model.gameevent.GameEventSokobanPersistentParams.COUNT_OF_BOXES_AND_HOMES;

public class SokobanPersistentSettings<ClientId> extends ViewTextFieldsJfx<ClientId> {
    public SokobanPersistentSettings(TransportOfClient<ClientId> transportOfClient, String viewName) {
        super(transportOfClient
                , GameEventSokobanPersistentParams.class
                , COMMON_LABEL_OF_PERSISTENT_PARAMS
                , viewName);
    }

    @Override
    protected String createStringFromGameEvent(GameEvent gameEvent) {
        GameEventSokobanPersistentParams ge = ((GameEventSokobanPersistentParams) gameEvent);
        return COUNT_OF_BOXES_AND_HOMES + ge.getCountOfBoxesAndHomes() + ".";
    }
}
