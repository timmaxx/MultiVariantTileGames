package timmax.tilegame.game.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public class GameEventMinesweeperVariableParamsFlag extends GameEvent {
    // ToDo: Разобраться и удалить ведущий '\n';
    public final static String COMMON_LABEL_OF_VARIABLE_PARAMS_FLAG = "\nVariable settings - flags:\n";
    public final static String FLAGS_WERE_USED = " Flags were used = ";
    public final static String FLAGS_ARE_STILL_AVAILABLE_FOR_USING = " Flags are still available for using = ";

    // final (в этом классе и в любом, который реализует Externalizable) пришлось убрать из-за readExternal.
    // Было-бы лучше конечно final оставить!
    // Да и конструктор без параметров - тоже для Externalizable, и лучше-бы без такого конструктора обойтись.
    // А так можно было-бы свой интерфейс сделать с конструктором, у которого был-бы параметром массив объектов!
    private /*final*/ int flagsWereUsed;
    private /*final*/ int flagsAreStillAvailableForUsing;

    public GameEventMinesweeperVariableParamsFlag() {
    }

    public GameEventMinesweeperVariableParamsFlag(
            int flagsWereUsed,
            int flagsAreStillAvailableForUsing) {
        this.flagsWereUsed = flagsWereUsed;
        this.flagsAreStillAvailableForUsing = flagsAreStillAvailableForUsing;
    }

    public int getFlagsWereUsed() {
        return flagsWereUsed;
    }

    public int getFlagsAreStillAvailableForUsing() {
        return flagsAreStillAvailableForUsing;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(flagsWereUsed);
        out.writeInt(flagsAreStillAvailableForUsing);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        flagsWereUsed = in.readInt();
        flagsAreStillAvailableForUsing = in.readInt();
    }
}
