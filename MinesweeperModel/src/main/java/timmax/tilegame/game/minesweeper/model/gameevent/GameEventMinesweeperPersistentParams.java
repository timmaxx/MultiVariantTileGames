package timmax.tilegame.game.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEventROTextFields;

public class GameEventMinesweeperPersistentParams extends GameEventROTextFields {
    // ToDo: Разобраться и удалить ведущий '\n';
    public final static String COMMON_LABEL_OF_PERSISTENT_PARAMS = "\nPersistent settings for Minesweeper\n";
    public final static String COUNT_OF_MINES = " Count of all mines in the field = ";

    // final (в этом классе и в любом, который реализует Externalizable) пришлось убрать из-за readExternal.
    // Было-бы лучше конечно final оставить!
    // Да и конструктор без параметров - тоже для Externalizable, и лучше-бы без такого конструктора обойтись.
    // А так можно было-бы свой интерфейс сделать с конструктором, у которого был-бы параметром массив объектов!
    private /*final*/ int countOfMines;


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