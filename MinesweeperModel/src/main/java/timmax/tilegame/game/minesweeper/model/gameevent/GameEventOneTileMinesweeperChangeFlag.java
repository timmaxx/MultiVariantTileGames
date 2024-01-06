package timmax.tilegame.game.minesweeper.model.gameevent;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javafx.scene.paint.Color;

import timmax.tilegame.basemodel.gameevent.GameEventOneTile;

import static timmax.tilegame.game.minesweeper.model.ModelOfServerOfMinesweeper.*;

public class GameEventOneTileMinesweeperChangeFlag extends GameEventOneTile {
    // final (в этом классе и в любом, который реализует Externalizable) пришлось убрать из-за readExternal.
    // Было-бы лучше конечно final оставить!
    // Да и конструктор без параметров - тоже для Externalizable, и лучше-бы без такого конструктора обойтись.
    // А так можно было-бы свой интерфейс сделать с конструктором, у которого был-бы параметром массив объектов!
    private /*final*/ boolean isFlag;

    public GameEventOneTileMinesweeperChangeFlag() {
    }

    public GameEventOneTileMinesweeperChangeFlag(int x, int y, boolean isFlag) {
        super(x, y);
        this.isFlag = isFlag;

        // ToDo: Ниже относится к визуализации. Удалить это отсюда.
        cellTextColor = Color.BLACK;
        if (isFlag) {
            this.cellBackgroundColor = FLAG_CELL_COLOR;
            cellText = FLAG;
        } else {
            this.cellBackgroundColor = UNOPENED_CELL_COLOR;
            cellText = "";
        }
    }
/*
    public boolean isFlag() {
        return isFlag;
    }
*/
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeBoolean(isFlag);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        isFlag = in.readBoolean();
    }
}
