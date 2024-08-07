package timmax.tilegame.game.minesweeper.jfx.view;

import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.tilegame.transport.TransportOfClient;
import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams;

import static timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams.COMMON_LABEL_OF_PERSISTENT_PARAMS;
import static timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams.COUNT_OF_MINES;

public class MinesweeperPersistentSettings extends ViewTextFieldsJfx {
    public MinesweeperPersistentSettings(TransportOfClient transportOfClient, String viewName) {
        super(transportOfClient
                , GameEventMinesweeperPersistentParams.class
                , COMMON_LABEL_OF_PERSISTENT_PARAMS
                , viewName);
    }

    @Override
    protected String createStringFromGameEvent(GameEvent gameEvent) {
        GameEventMinesweeperPersistentParams ge = ((GameEventMinesweeperPersistentParams) gameEvent);
        return COUNT_OF_MINES + ge.getCountOfMines() + ".";
    }
}
