package timmax.tilegame.game.sokoban.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventSokobanVariableParamsCountOfBoxesInHouses extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n'
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_BOXES_IN_HOMES = "\nVariable settings - Count of boxes in houses = ";

    private int countOfBoxesInHouses;

    public GameEventSokobanVariableParamsCountOfBoxesInHouses() {
        super();
    }

    public GameEventSokobanVariableParamsCountOfBoxesInHouses(
            int countOfBoxesInHouses) {
        this();
        this.countOfBoxesInHouses = countOfBoxesInHouses;
    }

    public int getCountOfBoxesInHouses() {
        return countOfBoxesInHouses;
    }

    @Override
    public String toString() {
        return "GameEventSokobanVariableParamsCountOfBoxesInHouses{" +
                "countOfBoxesInHouses=" + countOfBoxesInHouses +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(countOfBoxesInHouses);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        countOfBoxesInHouses = in.readInt();
    }
}
