package timmax.tilegame.minesweeper.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.basemodel.protocol.server.GameType;
import timmax.tilegame.transport.ISenderOfEventOfClient;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams;

import static timmax.tilegame.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams.COMMON_LABEL_OF_PERSISTENT_PARAMS;
import static timmax.tilegame.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams.COUNT_OF_MINES;

public class MinesweeperPersistentSettings extends ViewTextFieldsJfx {
    public MinesweeperPersistentSettings(ISenderOfEventOfClient senderOfEventOfClient, String viewName, GameType gameType) {
        super(senderOfEventOfClient
                , GameEventMinesweeperPersistentParams.class
                , COMMON_LABEL_OF_PERSISTENT_PARAMS
                , viewName
                , gameType);
    }

    @Override
    protected String createStringFromGameEvent(GameEvent gameEvent) {
        GameEventMinesweeperPersistentParams ge = ((GameEventMinesweeperPersistentParams) gameEvent);
        return COUNT_OF_MINES + ge.getCountOfMines() + ".";
    }
}
