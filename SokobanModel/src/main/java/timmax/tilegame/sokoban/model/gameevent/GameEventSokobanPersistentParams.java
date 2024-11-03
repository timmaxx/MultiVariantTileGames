package timmax.tilegame.sokoban.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventSokobanPersistentParams extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n'
    public final static String COMMON_LABEL_OF_PERSISTENT_PARAMS = "\nPersistent settings for Sokoban\n";
    public final static String COUNT_OF_BOXES_AND_HOMES = " Count of all boxes and homes = ";

    private int countOfBoxesAndHomes;

    public GameEventSokobanPersistentParams() {
        super();
    }
/*
    public GameEventSokobanPersistentParams(
            int countOfBoxesAndHomes) {
        this();
        this.countOfBoxesAndHomes = countOfBoxesAndHomes;
    }
*/
    public int getCountOfBoxesAndHomes() {
        return countOfBoxesAndHomes;
    }

    @Override
    public String toString() {
        return "GameEventSokobanPersistentParams{" +
                "countOfBoxesAndHomes=" + countOfBoxesAndHomes +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(countOfBoxesAndHomes);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        countOfBoxesAndHomes = in.readInt();
    }
}
