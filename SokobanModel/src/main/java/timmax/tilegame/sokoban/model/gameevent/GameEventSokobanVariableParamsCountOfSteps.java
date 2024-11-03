package timmax.tilegame.sokoban.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventSokobanVariableParamsCountOfSteps extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n'
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_STEPS = "\nVariable settings - Count of steps = ";

    private int countOfSteps;

    public GameEventSokobanVariableParamsCountOfSteps() {
        super();
    }
/*
    public GameEventSokobanVariableParamsCountOfSteps(
            int countOfSteps) {
        this();
        this.countOfSteps = countOfSteps;
    }
*/
    public int getCountOfSteps() {
        return countOfSteps;
    }

    @Override
    public String toString() {
        return "GameEventSokobanVariableParamsCountOfSteps{" +
                "countOfSteps=" + countOfSteps +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(countOfSteps);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        countOfSteps = in.readInt();
    }
}
