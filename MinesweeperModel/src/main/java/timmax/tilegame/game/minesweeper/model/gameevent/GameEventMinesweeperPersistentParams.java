package timmax.tilegame.game.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventMinesweeperPersistentParams extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n';
    public final static String COMMON_LABEL_OF_PERSISTENT_PARAMS = "\nPersistent settings for Minesweeper\n";
    public final static String COUNT_OF_MINES = " Count of all mines in the field = ";

    private int countOfMines;

    public GameEventMinesweeperPersistentParams() {
    }

    public GameEventMinesweeperPersistentParams(int countOfMines) {
        this.countOfMines = countOfMines;
    }

    public int getCountOfMines() {
        return countOfMines;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(countOfMines);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        countOfMines = in.readInt();
    }
}
