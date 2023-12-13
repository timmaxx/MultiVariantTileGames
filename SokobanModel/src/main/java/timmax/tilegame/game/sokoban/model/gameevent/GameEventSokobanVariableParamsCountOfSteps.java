package timmax.tilegame.game.sokoban.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventSokobanVariableParamsCountOfSteps extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n'
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_STEPS = "\nVariable settings - Count of steps = ";

    // final (в этом классе и в любом, который реализует Externalizable) пришлось убрать из-за readExternal.
    // Было-бы лучше конечно final оставить!
    // Да и конструктор без параметров - тоже для Externalizable, и лучше-бы без такого конструктора обойтись.
    // А так можно было-бы свой интерфейс сделать с конструктором, у которого был-бы параметром массив объектов!
    private /*final*/ int countOfSteps;


    public GameEventSokobanVariableParamsCountOfSteps() {
        super();
    }

    public GameEventSokobanVariableParamsCountOfSteps(
            int countOfSteps) {
        this();
        this.countOfSteps = countOfSteps;
    }

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