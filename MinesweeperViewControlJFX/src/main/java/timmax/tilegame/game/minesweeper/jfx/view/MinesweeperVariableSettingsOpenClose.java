package timmax.tilegame.game.minesweeper.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfClient;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsOpenClose;

import static timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsOpenClose.*;

public class MinesweeperVariableSettingsOpenClose<ClientId> extends ViewTextFieldsJfx<ClientId> {
    public MinesweeperVariableSettingsOpenClose(TransportOfClient<ClientId> transportOfClient, String viewName) {
        super(transportOfClient
                , GameEventMinesweeperVariableParamsOpenClose.class
                , COMMON_LABEL_OF_VARIABLE_PARAMS_OPEN_CLOSE
                , viewName);
    }

    @Override
    protected String createStringFromGameEvent(GameEvent gameEvent) {
        GameEventMinesweeperVariableParamsOpenClose ge = ((GameEventMinesweeperVariableParamsOpenClose) gameEvent);
        return TILES_WERE_OPENED + ge.getTilesWereOpened() + ". " +
                TILES_STILL_CLOSED + ge.getTilesStillClosed() + ". "
                ;
    }
}
