package timmax.tilegame.game.minesweeper.jfx.view;

import timmax.tilegame.basemodel.BaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;

import timmax.tilegame.guiengine.jfx.view.ViewTextFieldsJfx;

import timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams;

import static timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams.COMMON_LABEL_OF_PERSISTENT_PARAMS;
import static timmax.tilegame.game.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams.COUNT_OF_MINES;

public class MinesweeperPersistentSettings extends ViewTextFieldsJfx {
    public MinesweeperPersistentSettings( BaseModel baseModel) {
        super( baseModel
                , GameEventMinesweeperPersistentParams.class
                , COMMON_LABEL_OF_PERSISTENT_PARAMS);
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventMinesweeperPersistentParams ge = ( ( GameEventMinesweeperPersistentParams) gameEvent);
        return COUNT_OF_MINES + ge.getCountOfMines( ) + ".";
    }
}