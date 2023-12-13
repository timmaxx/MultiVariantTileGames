package timmax.tilegame.game.sokoban.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventSokobanVariableParamsCountOfBoxesInHouses extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n'
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_COUNT_OF_BOXES_IN_HOMES = "\nVariable settings - Count of boxes in houses = ";

    // final (в этом классе и в любом, который реализует Externalizable) пришлось убрать из-за readExternal.
    // Было-бы лучше конечно final оставить!
    // Да и конструктор без параметров - тоже для Externalizable, и лучше-бы без такого конструктора обойтись.
    // А так можно было-бы свой интерфейс сделать с конструктором, у которого был-бы параметром массив объектов!
    private /*final*/ int countOfBoxesInHouses;


    public GameEventSokobanVariableParamsCountOfBoxesInHouses() {
    }

    public GameEventSokobanVariableParamsCountOfBoxesInHouses(
            int countOfBoxesInHouses) {
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