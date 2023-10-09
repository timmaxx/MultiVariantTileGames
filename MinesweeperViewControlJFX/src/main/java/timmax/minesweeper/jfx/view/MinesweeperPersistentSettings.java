package timmax.minesweeper.jfx.view;

import timmax.tilegame.basemodel.ConnectionToBaseModel;
import timmax.tilegame.basemodel.gameevent.GameEvent;
import timmax.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams;
import timmax.tilegameenginejfx.*;

import static timmax.minesweeper.model.gameevent.GameEventMinesweeperPersistentParams.*;

public class MinesweeperPersistentSettings extends ViewTextFieldsJfx {
    public MinesweeperPersistentSettings( ConnectionToBaseModel connectionToBaseModel) {
        super( connectionToBaseModel
                , GameEventMinesweeperPersistentParams.class
                , COMMON_LABEL_OF_PERSISTENT_PARAMS);
    }

    @Override
    protected String createStringFromGameEvent( GameEvent gameEvent) {
        GameEventMinesweeperPersistentParams ge = ( ( GameEventMinesweeperPersistentParams) gameEvent);
        return COUNT_OF_MINES + ge.getCountOfMines( ) + ".";
    }
}