package timmax.tilegame.minesweeper.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.transport.TransportOfClient;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsFlag;

import static timmax.tilegame.minesweeper.model.gameevent.GameEventMinesweeperVariableParamsFlag.*;

public class MinesweeperVariableSettingsFlag extends ViewTextFieldsJfx {
    public MinesweeperVariableSettingsFlag(TransportOfClient transportOfClient, String viewName, GameType gameType) {
        super(transportOfClient
                , GameEventMinesweeperVariableParamsFlag.class
                , COMMON_LABEL_OF_VARIABLE_PARAMS_FLAG
                , viewName
                , gameType);
    }

    @Override
    protected String createStringFromGameEvent(GameEvent gameEvent) {
        GameEventMinesweeperVariableParamsFlag ge = ((GameEventMinesweeperVariableParamsFlag) gameEvent);
        return FLAGS_WERE_USED + ge.getFlagsWereUsed() + ". " +
                FLAGS_ARE_STILL_AVAILABLE_FOR_USING + ge.getFlagsAreStillAvailableForUsing() + ". "
                ;
    }
}
